PROCEDURE prc_search_nhankhau_macd(p_ma_tinh      in varchar,
                                     p_ma_huyen     in varchar,
                                     p_ma_xa        in varchar,
                                     p_ma_thon      in varchar,
                                     p_ma_ho        in varchar,
                                     p_ten_ho       in varchar,
                                     p_ma_cd        in varchar,
                                     p_ho_ten       in varchar,
                                     p_check_ho_ten in varchar,
                                     p_so_cmtnd     in varchar,
                                     p_ma_the       in varchar,
                                     p_ngay_sinh    in varchar,
                                     p_ma_so        in varchar,
                                     p_page_current IN INTEGER default 1, -- Trang hien tai
                                     p_page_size    IN INTEGER default 10, -- So luong Chung tu / 1 trang
                                     p_page_count   OUT VARCHAR2, -- Tong so trang
                                     p_total_count  OUT VARCHAR2, -- Tong so du lieu
                                     p_data         out out_cursor) IS
    v_exception EXCEPTION;
    v_error       VARCHAR2(200);
    c_data        out_cursor;
    c_count       out_cursor;
    v_row_begin   INT;
    v_row_end     INT;
    v_sql_count   long;
    v_sql_page    long;
    v_sql         long;
    v_where       long;
    v_page_count  VARCHAR2(15);
    v_total_count VARCHAR2(15);
    v_ngay_sinh   VARCHAR2(15);
    v_nam_sinh    VARCHAR2(5);
  BEGIN

    /*    process_pkg.insert_sql(p_ngay_sinh, 'test');*/
    v_row_begin := (p_page_current - 1) * p_page_size + 1;
    v_row_end   := p_page_current * p_page_size;

    v_ngay_sinh := vss.vss_pck_function.FNC_NGAY2NUMBER_FULL(p_ngay_sinh);

    IF (v_ngay_sinh is not null and SUBSTR(v_ngay_sinh, 5, 4) = '0000') THEN
      v_nam_sinh := SUBSTR(v_ngay_sinh, 1, 4);
    ELSE
      v_nam_sinh := null;
    END IF;

    IF (p_ma_tinh is null and p_ma_cd is null ) THEN
      v_error := 'VSS-05:MA_TINH KHONG DE TRONG';
      RAISE v_exception;
    END IF;

    IF (p_ho_ten is null ) THEN
      v_error := 'VSS-05:HO_TEN KHONG DE TRONG';
      RAISE v_exception;
    END IF;

    IF (p_ma_ho is null AND p_ten_ho is null AND p_so_cmtnd is null AND
       p_ma_the is null AND p_ma_so is null AND p_ma_cd is null AND
       p_ngay_sinh is null) THEN
      v_error := 'VSS-05:PHAI NHAP IT NHAT 1 TIEU CHI TIM KIEM KHAC CON LAI';
      RAISE v_exception;
    END IF;

    v_sql_count := 'SELECT (TRUNC((COUNT(1)-1)/' || p_page_size ||
                   ' + 1)) AS TS_TRANG, COUNT(1) AS TS_NK FROM VSS_HO_GD_NHANKHAU  NK ';
    v_sql       := 'SELECT
             NK.MA_TINH,
             NK.MA_HUYEN,
             NK.MA_XA,
             NK.MA_THON,
             NK.TEN_THON,
             NK.TEN_XA,
             NK.TEN_HUYEN,
             NK.TEN_TINH,
             NK.HO_TEN,
             NK.MA_CD,
             NK.MA_THE,
             NK.MA_SO,
             NK.NGAY_SINH,
             NK.GIOI_TINH,
             NK.MA_HO_GD,
             NK.SO_CMTND,
             NK.TEN_QH_CHUHO,
             NK.MA_NV,
             NK.sync_tst,
             to_char(NK.CREATED_DATE,''dd/MM/yyyy'') CREATED_DATE,
            (ROW_NUMBER() OVER (ORDER BY  NK.MA_CD ASC)) AS RN
        FROM VSS_HO_GD_NHANKHAU  NK ';

    --v_where     := ' WHERE 1=1 AND NK.MA_TINH = ''' || p_ma_tinh || '''';

    IF p_ma_tinh is not null THEN
        v_where     := ' WHERE 1=1 AND NK.MA_TINH = ''' || p_ma_tinh || '''';
    ELSE
        v_where     := ' WHERE 1=1 ';
    END IF;

    IF (p_ma_huyen is not null) THEN
      v_where := v_where || ' AND (NK.MA_HUYEN=''' || p_ma_huyen || ''')';
    END IF;
    IF (p_ma_xa is not null) THEN
      v_where := v_where || ' AND (NK.MA_XA=''' || p_ma_xa || ''')';
    END IF;
    IF (p_ma_thon is not null) THEN
      v_where := v_where || ' AND (NK.MA_THON=''' || p_ma_thon || ''')';
    END IF;

    IF (p_ma_ho is not null) THEN
      v_where := v_where || ' AND (NK.MA_HO_GD=''' || p_ma_ho || ''')';
    END IF;

    IF (p_ma_cd is not null) THEN
      v_where := v_where || ' AND (NK.MA_CD=''' || p_ma_cd || ''')';
    END IF;

    IF (p_check_ho_ten is not null) THEN
        v_where := v_where || ' AND ((UPPER(NK.HO_TEN)) = ''%' ||
                 (UPPER(p_ho_ten)) || '%'')';
    ELSIF (p_ho_ten is not null) THEN
      v_where := v_where || ' AND ((UPPER(NK.HO_TEN)) = ''' ||
                 (UPPER(p_ho_ten)) || ''')';

    END IF;

    IF (p_so_cmtnd is not null) THEN
      v_where := v_where || ' AND (NK.SO_CMTND = ''' ||
                 TRIM(p_so_cmtnd) || ''')';
    END IF;

    IF (p_ma_the is not null) THEN
      v_where := v_where || ' AND (NK.MA_THE = ''' ||
                 TRIM(UPPER(p_ma_the)) || ''')';
    END IF;

    IF v_nam_sinh is not null THEN
      v_where := v_where || ' AND (SUBSTR(NK.NGAY_SINH,1,4) = ''' ||
                 v_nam_sinh || ''')';
    ELSIF (p_ngay_sinh is not null) THEN
      v_where := v_where || ' AND (NK.NGAY_SINH = ''' || v_ngay_sinh ||
                 ''')';
    END IF;

    IF (p_ma_so is not null) THEN
      v_where := v_where || ' AND (NK.MA_SO = ''' ||
                 TRIM(UPPER(p_ma_so)) || ''')';
    END IF;

    v_where := v_where || '  AND (trang_thai_duyet is null OR TRANG_THAI_DUYET NOT IN (1,3,4))';
    v_where := v_where || '  and (NK.TYPEID is NULL or NK.TYPEID NOT IN (''GT'', ''GM'', ''GK'', ''GG''))';

    v_sql_count := v_sql_count || v_where;

    v_sql := v_sql || v_where;

    v_sql_page := 'SELECT * FROM (' || v_sql || ') WHERE (RN>=' ||
                  v_row_begin || ') AND (RN <=' || v_row_end || ')' ||
                  'ORDER BY RN ASC';

    dbms_output.put_line(v_sql_page);
    OPEN c_count FOR v_sql_count;
    IF (c_count%NOTFOUND) THEN
      v_page_count  := '0';
      v_total_count := '0';
    END IF;

    FETCH c_count
      INTO v_page_count, v_total_count;

    IF c_count%ISOPEN THEN
      CLOSE c_count;
    END IF;

    OPEN c_data FOR v_sql_page;

    p_page_count  := v_page_count;
    p_total_count := v_total_count;
    p_data        := c_data;
  EXCEPTION
    WHEN v_exception THEN
      IF c_count%ISOPEN THEN
        CLOSE c_count;
      END IF;

      IF c_data%ISOPEN THEN
        CLOSE c_data;
      END IF;
      v_error := 'Error in PROCEDURE prc_search_nhankhau_macd: ' || v_error;
      raise_application_error(-20001, v_error);
    WHEN OTHERS THEN
      RAISE;
  END;