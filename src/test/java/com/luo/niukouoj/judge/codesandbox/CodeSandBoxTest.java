package com.luo.niukouoj.judge.codesandbox;

import com.luo.niukouoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.luo.niukouoj.judge.codesandbox.model.ExecuteCodeResponse;
import com.luo.niukouoj.model.enums.QuestionSubmitLanguageEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;


/**
 * @author 木南
 * @version 1.0
 * @Description TODO
 */
@SpringBootTest
class CodeSandBoxTest {

    @Value("${codesandbox.type}")
    private String type;

    @Test
    void executeCode() {
        CodeSandBox codeSandbox = CodeSandBoxFactory.getInstance().newInstance("remote");
        String code = "int main() { }";
        String language = QuestionSubmitLanguageEnum.JAVA.getValue();
        List<String> inputCaseList = Arrays.asList("1 2", "3 4");
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inputCaseList(inputCaseList)
                .build();
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);
        Assertions.assertNotNull(executeCodeResponse);
    }

    @Test
    void executeCodeByValue() {
        CodeSandBox codeSandbox = CodeSandBoxFactory.getInstance().newInstance(type);
        String code = "int main() { }";
        String language = QuestionSubmitLanguageEnum.JAVA.getValue();
        List<String> inputCaseList = Arrays.asList("1 2", "3 4");
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inputCaseList(inputCaseList)
                .build();
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);
        Assertions.assertNotNull(executeCodeResponse);
    }


    @Test
    void executeCodeByProxy() {
        CodeSandBox codeSandbox = CodeSandBoxFactory.getInstance().newInstance(type);
        codeSandbox = new CodeSandBoxProxy(codeSandbox);
        String code = "public class Main {\n" +
                "    public static void main(String[] args) {\n" +
                "        int a = Integer.parseInt(args[0]);\n" +
                "        int b = Integer.parseInt(args[1]);\n" +
                "        System.out.println(\"结果:\" + (a + b));\n" +
                "    }\n" +
                "}";
        String language = QuestionSubmitLanguageEnum.JAVA.getValue();
        List<String> inputCaseList = Arrays.asList("1 2", "3 4");
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inputCaseList(inputCaseList)
                .build();
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);
        Assertions.assertNotNull(executeCodeResponse);
    }

}