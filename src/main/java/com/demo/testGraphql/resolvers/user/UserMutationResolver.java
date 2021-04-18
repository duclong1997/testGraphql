package com.demo.testGraphql.resolvers.user;

import com.demo.testGraphql.context.CustomGraphQlContext;
import com.demo.testGraphql.models.dtos.RegisterUser;
import com.demo.testGraphql.models.dtos.UserDto;
import com.demo.testGraphql.services.UserService;
import com.demo.testGraphql.utils.IOUtil;
import graphql.GraphQLException;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.servlet.http.Part;
import javax.validation.Valid;
import java.io.*;

@Component
@RequiredArgsConstructor
@Slf4j
@Validated
public class UserMutationResolver implements GraphQLMutationResolver {

    @Autowired
    private UserService userService;

    // not Authenticated
    @PreAuthorize("isAnonymous()")
    public UserDto login(String username, String password) {
        return userService.authen(username, password);
    }

    @PreAuthorize("isAnonymous()")
    public UserDto register(@Valid RegisterUser userRegister) {
        return this.userService.register(userRegister);
    }

    // sử dụng với form-data
    // upload file
    public UserDto uploadFileImg(Part avatar, DataFetchingEnvironment environment) throws IOException {

        // custom context
        CustomGraphQlContext contextCustom = environment.getContext();
        log.info("user id: {}", contextCustom.getUserId());

        // get file in argument "avatar"
        Part part = environment.getArgument("avatar");
        InputStream initStream = null;
        OutputStream outStream = null;
        try {
            log.info("file data: {} ", part.getInputStream());
            log.info("name file: {}, size file: {}", part.getSubmittedFileName(), part.getSize());
            initStream = part.getInputStream();

            String path = "src/main/resources/images";
            File targetFile = new File(path);
            if (!targetFile.exists()) {
                targetFile.mkdir();
            }
            File file = new File(path + "/" + part.getSubmittedFileName());
            byte[] buffer = new byte[initStream.available()];
            initStream.read(buffer);
            outStream = new FileOutputStream(file);
            outStream.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
            throw new GraphQLException(e.getMessage());
        } finally {
            // close inputStream
            IOUtil.closeQuietly(initStream);
            // close outputStream
            IOUtil.closeQuietly(outStream);
        }
        return new UserDto();
    }
}
