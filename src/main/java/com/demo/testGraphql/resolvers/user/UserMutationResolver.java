package com.demo.testGraphql.resolvers.user;

import com.demo.testGraphql.models.dtos.RegisterUser;
import com.demo.testGraphql.models.dtos.UserDto;
import com.demo.testGraphql.services.UserService;
import graphql.kickstart.servlet.context.DefaultGraphQLServletContext;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserMutationResolver implements GraphQLMutationResolver {

    @Autowired
    private UserService userService;

    // not Authenticated
    @PreAuthorize("isAnonymous()")
    public UserDto login(String username, String password) {
        return userService.authen(username, password);
    }

    @PreAuthorize("isAnonymous()")
    public UserDto register(RegisterUser userRegister) {
        return this.userService.register(userRegister);
    }

    // sử dụng với form-data
    // upload file
    public UserDto uploadFileImg(DataFetchingEnvironment environment) {
        DefaultGraphQLServletContext context = environment.getContext();

        context.getFileParts().forEach(part -> {
            log.info("upload file: {}, size {}", part.getSubmittedFileName(), part.getSize());
            try {
                log.info("file data: {} ", part.getInputStream());
                InputStream initialStream = part.getInputStream();
                String path = "src/main/resources/images";
                File targetFile = new File(path);
                if (!targetFile.exists()) {
                    targetFile.mkdir();
                }
                File file = new File(path + "/" + part.getSubmittedFileName());
                byte[] buffer = new byte[initialStream.available()];
                initialStream.read(buffer);
                OutputStream outStream = new FileOutputStream(file);
                outStream.write(buffer);
                outStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return new UserDto();
    }
}
