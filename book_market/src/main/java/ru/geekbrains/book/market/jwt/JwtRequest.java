package ru.geekbrains.book.market.jwt;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class JwtRequest {

    @ApiModelProperty(notes = "Username", required = true, position = 1, example = "ivanov_sergey@mail.com")
    private String username;

    @ApiModelProperty(notes = "Password", required = true, position = 2, example = "manager1")
    private String password;
}
