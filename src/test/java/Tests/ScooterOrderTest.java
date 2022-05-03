package Tests;

import pages.MainPage;
import pages.OrderPage;
import com.codeborne.selenide.Configuration;
import org.junit.BeforeClass;
import org.junit.Test;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class ScooterOrderTest {
    public final String testURL = "https://qa-scooter.praktikum-services.ru/";

   /* @BeforeClass     // Для проверки на не-Хроме, на эдж работает
    public static void setProperties() {
        Configuration.browser = "edge";}*/
        @BeforeClass
    public static void setProperties() {
        Configuration.browser = "Chrome";}
    @Test
    public void orderTestHeaderOrderButton() {
        MainPage mainPage = open(testURL, MainPage.class );
        mainPage.hideCookiDisclamer();
        mainPage.headerOrderButtonClick();
        OrderPage orderPage = page(OrderPage.class);
        orderPage.isOrderPageTitleVisible();
        orderPage.setClient("Иван", "Петров", "+79123456789");
        orderPage.setAddress("Москва, улица Ленина, д. 1");
        orderPage.setMetro();
        orderPage.nextButtonClick();
        //Происходит переход на вторую часть формы заказа
        orderPage.setRandomOrderDate();
        orderPage.setRandomOrderDuration();
        orderPage.chooseColor();
        orderPage.addComment();
        orderPage.nextButtonClick();
        //Вторая часть формы заполнена
        orderPage.confirmOrder();
        orderPage.isOrderConfirmed();
    }

    @Test
    public void orderTestMiddleOrderButton() {
        MainPage mainPage = open(testURL, MainPage.class );
        mainPage.hideCookiDisclamer();
        mainPage.middleOrderButtonClick();
        OrderPage orderPage = page(OrderPage.class);
        orderPage.isOrderPageTitleVisible();
        orderPage.setClient("Игорь", "Сидоров", "+79124564824");
        orderPage.setAddress("Москва, улица Советская, д. 28");
        orderPage.setMetro();
        orderPage.nextButtonClick();
        //Происходит переход на вторую часть формы заказа
        orderPage.setRandomOrderDate();
        orderPage.setRandomOrderDuration();
        orderPage.chooseColor();
        orderPage.addComment();
        orderPage.nextButtonClick();
        //Вторая часть формы заполнена
        orderPage.confirmOrder();
        orderPage.isOrderConfirmed();
    }

}