package ru.netology;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class NewCardFormTest {
    @Test

    void shouldRespond200() throws InterruptedException {

        open("http://localhost:9999");
        SelenideElement name = $("[data-test-id=name]");
        name.$(".input__control").setValue("Римский-Корсаков Николай");
        SelenideElement phone = $("[data-test-id=phone]");
        phone.$(".input__control").setValue("+79012345678");
        SelenideElement agreement = $("[data-test-id=agreement]");
        agreement.$(".checkbox__box").click();
        SelenideElement button = $(".button");
        button.click();
        $("[data-test-id=order-success]").shouldHave(Condition.exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));




    }

}
