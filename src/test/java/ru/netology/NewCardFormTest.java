package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class NewCardFormTest {
    @Test
    void shouldRespond200() throws InterruptedException {

        open("http://localhost:9999");
        $("[data-test-id='name'] input").setValue("Римский-Корсаков Николай");
        $("[data-test-id='phone'] input").setValue("+79012345678");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=order-success]").shouldHave(Condition.exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void noAgreementFlag() throws InterruptedException {

        open("http://localhost:9999");
        $("[data-test-id='name'] input").setValue("Павел");
        $("[data-test-id='phone'] input").setValue("+79012345678");
        $(".button").click();
        $("[data-test-id=agreement].input_invalid").shouldBe(Condition.visible);
    }

    @Test
    void phoneTwelveNumbers() throws InterruptedException {

        open("http://localhost:9999");
        $("[data-test-id='name'] input").setValue("Римский-Корсаков Николай");
        $("[data-test-id='phone'] input").setValue("+790123456780");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void phoneTenNumbers() throws InterruptedException {

        open("http://localhost:9999");
        $("[data-test-id='name'] input").setValue("Римский-Корсаков Николай");
        $("[data-test-id='phone'] input").setValue("+7901234567");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void phoneLetters() throws InterruptedException {

        open("http://localhost:9999");
        $("[data-test-id='name'] input").setValue("Римский-Корсаков Николай");
        $("[data-test-id='phone'] input").setValue("Римский-Корсаков Николай");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void nameFieldEmpty() throws InterruptedException {

        open("http://localhost:9999");
        $("[data-test-id='phone'] input").setValue("+79012345678");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void phoneFieldEmpty() throws InterruptedException {

        open("http://localhost:9999");
        $("[data-test-id='name'] input").setValue("Римский-Корсаков Николай");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void nameInEnglish() throws InterruptedException {

        open("http://localhost:9999");
        $("[data-test-id='name'] input").setValue("Pavel");
        $("[data-test-id='phone'] input").setValue("+79012345678");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void nameWithDot() throws InterruptedException {

        open("http://localhost:9999");
        $("[data-test-id='name'] input").setValue("Павел.");
        $("[data-test-id='phone'] input").setValue("+79012345678");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void nameWithComma() throws InterruptedException {

        open("http://localhost:9999");
        $("[data-test-id='name'] input").setValue("Павел,");
        $("[data-test-id='phone'] input").setValue("+79012345678");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void nameWithExclamation() throws InterruptedException {

        open("http://localhost:9999");
        $("[data-test-id='name'] input").setValue("Павел!");
        $("[data-test-id='phone'] input").setValue("+79012345678");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void nameWithQuestion() throws InterruptedException {

        open("http://localhost:9999");
        $("[data-test-id='name'] input").setValue("Павел?");
        $("[data-test-id='phone'] input").setValue("+79012345678");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void nameWithPlus() throws InterruptedException {

        open("http://localhost:9999");
        $("[data-test-id='name'] input").setValue("Павел+");
        $("[data-test-id='phone'] input").setValue("+79012345678");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void nameWithEqual() throws InterruptedException {

        open("http://localhost:9999");
        $("[data-test-id='name'] input").setValue("Павел=");
        $("[data-test-id='phone'] input").setValue("+79012345678");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void nameWithRoundBracket() throws InterruptedException {

        open("http://localhost:9999");
        $("[data-test-id='name'] input").setValue("Павел)");
        $("[data-test-id='phone'] input").setValue("+79012345678");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void nameWithBracket() throws InterruptedException {

        open("http://localhost:9999");
        $("[data-test-id='name'] input").setValue("Павел]");
        $("[data-test-id='phone'] input").setValue("+79012345678");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void nameWithAsterisk() throws InterruptedException {

        open("http://localhost:9999");
        $("[data-test-id='name'] input").setValue("Павел*");
        $("[data-test-id='phone'] input").setValue("+79012345678");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void nameWithDollarSign() throws InterruptedException {

        open("http://localhost:9999");
        $("[data-test-id='name'] input").setValue("Павел$");
        $("[data-test-id='phone'] input").setValue("+79012345678");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void nameWithAndSign() throws InterruptedException {

        open("http://localhost:9999");
        $("[data-test-id='name'] input").setValue("Павел&");
        $("[data-test-id='phone'] input").setValue("+79012345678");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void nameWithPercent() throws InterruptedException {

        open("http://localhost:9999");
        $("[data-test-id='name'] input").setValue("Павел%");
        $("[data-test-id='phone'] input").setValue("+79012345678");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void nameWithSharpSign() throws InterruptedException {

        open("http://localhost:9999");
        $("[data-test-id='name'] input").setValue("Павел#");
        $("[data-test-id='phone'] input").setValue("+79012345678");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void nameWithAtSign() throws InterruptedException {

        open("http://localhost:9999");
        $("[data-test-id='name'] input").setValue("Павел@");
        $("[data-test-id='phone'] input").setValue("+79012345678");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void nameWithApostrophe() throws InterruptedException {

        open("http://localhost:9999");
        $("[data-test-id='name'] input").setValue("Павел`");
        $("[data-test-id='phone'] input").setValue("+79012345678");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void nameWithTilde() throws InterruptedException {

        open("http://localhost:9999");
        $("[data-test-id='name'] input").setValue("Павел~");
        $("[data-test-id='phone'] input").setValue("+79012345678");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void nameWithUnderscore() throws InterruptedException {

        open("http://localhost:9999");
        $("[data-test-id='name'] input").setValue("Павел_");
        $("[data-test-id='phone'] input").setValue("+79012345678");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void nameWithArrow() throws InterruptedException {

        open("http://localhost:9999");
        $("[data-test-id='name'] input").setValue("Павел>");
        $("[data-test-id='phone'] input").setValue("+79012345678");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void nameWithSlash() throws InterruptedException {

        open("http://localhost:9999");
        $("[data-test-id='name'] input").setValue("Павел/");
        $("[data-test-id='phone'] input").setValue("+79012345678");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void nameWithFigureBracket() throws InterruptedException {

        open("http://localhost:9999");
        $("[data-test-id='name'] input").setValue("Павел}");
        $("[data-test-id='phone'] input").setValue("+79012345678");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void nameWithQuotation() throws InterruptedException {

        open("http://localhost:9999");
        $("[data-test-id='name'] input").setValue("\"Павел\"");
        $("[data-test-id='phone'] input").setValue("+79012345678");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }


}
