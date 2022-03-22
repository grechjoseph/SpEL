package com.jg.spel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class MyController {

    @PostMapping
    public String execute(@RequestBody final SpelExecuteRequest request) {
        final String fullExpression = String.format(request.getSpelExpression(), request.getVariables().toArray());
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression(fullExpression);
        return exp.getValue(String.class);
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static class SpelExecuteRequest {

        // The examples below add two numbers.
        @Builder.Default
        private String spelExpression = "%s + %s";

        @Builder.Default
        private List<String> variables = Arrays.asList("2", "4");

    }

}
