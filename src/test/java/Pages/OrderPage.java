package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class OrderPage {
    @FindBy(how = How.CLASS_NAME, using = "Order_Header__BZXOb")
    public SelenideElement orderPageTitle; // Заголовок экрана заказа (Текст на разных экранах разный, локатор один)
    public void isOrderPageTitleVisible(){
        orderPageTitle.shouldBe(Condition.visible);
    }
    @FindBy(how = How.XPATH, using = "//div[@class='Input_InputContainer__3NykH']/input")
    public ElementsCollection clientData; // формы заказа, 1- Имя, 2-Фамилия, 3- Адрес, 4- Телефон;
    public void setClient(String name, String surName, String phone){
        clientData.get(1).sendKeys(name);
        clientData.get(2).sendKeys(surName);
        clientData.get(4).sendKeys(phone);
    }
    public void setAddress(String address){
        clientData.get(3).sendKeys(address);

    }
    @FindBy(how = How.XPATH, using = "//input[@class='select-search__input']")
    public SelenideElement clientMetro; // поле ввода станции метро
    @FindBy(how = How.XPATH, using = "//div[@class='select-search__select']")
    public SelenideElement stationSelector; // выпадающее мень со списком станций
    public void setMetro(){ // Установить станцию метро
        clientMetro.click();
        clientMetro.sendKeys(Keys.DOWN);
        clientMetro.pressEnter();
        //stationSelector.shouldBe(Condition.visible);
        //stationSelector.setValue(metro).click();
        //clientMetro.click();
    }
    @FindBy(how = How.XPATH, using = "//button[@class='Button_Button__ra12g Button_Middle__1CSJM']")
    public SelenideElement nextButton; // Клавиша "Далее", совпадает с коавишей "Заказать" со следующего экрана по локатору
    public void nextButtonClick(){
        nextButton.click();
    }
    // Второй экран формы заказа
    @FindBy(how = How.XPATH, using = "//input[@placeholder='* Когда привезти самокат']")
    public SelenideElement orderDate; // поле ввода даты заказа
    public void setRandomOrderDate(){
        int year = (int)(Math.random()*(5))+2022; // случайный год от 2022 до 2027
        int month =(int)(Math.random()*(12))+1; // случайны месяц от 1 до 12
        int maxDay=31;
        switch(month){
            case(2): maxDay=28; break;
            case(4): maxDay=30; break;
            case(6): maxDay=30; break;
            case(9): maxDay=30; break;
            case(11): maxDay=30; break;
        }
        int day = (int)(Math.random()*(maxDay))+1; //случайный день в промежутке, характерном для количества дней в месяце
        orderDate.sendKeys(day+"."+month+"."+year);
        orderDate.pressEnter();
    }
    @FindBy(how = How.XPATH, using = "//div[@class='Dropdown-control']")
    public SelenideElement orderDuration; // поле длительности заказа
    @FindBy(how = How.XPATH, using = "//div[@class='Dropdown-option']")
    public ElementsCollection orderDurationOptions; // формы заказа, 1- Фамилия, 2-Имя, 3- Адрес, 4- Метро, 5-Телефон
    public void setRandomOrderDuration(){ // Установка случайной длительности заказа из имеющихся вариантов
        int n=(int)(Math.random()*(7));
        orderDuration.shouldBe(Condition.visible);
        orderDuration.click();
        orderDurationOptions.get(n).click();
    }
    @FindBy(how = How.XPATH, using = "//input[@id='black']")
    public SelenideElement colorCheckboxBlack; // Чек-бокс цвета, черный
    @FindBy(how = How.XPATH, using = "//input[@id='grey']")
    public SelenideElement colorCheckboxGrey; // чек-бокс цвета, серый
    public void chooseColor(){ // Случайно определяем состояние чек-бокса: черны, серый или оба сразу
        int rand =(int)(Math.random()*(3));
        switch (rand){
            case(0): colorCheckboxBlack.click(); break;
            case(1): colorCheckboxGrey.click(); break;
            case(2): colorCheckboxBlack.click();
            colorCheckboxGrey.click(); break;
        }
    }
    @FindBy(how = How.XPATH, using = "//input[@placeholder='Комментарий для курьера']")
    public SelenideElement commentField; // комментарий для курьера
    public void addComment(){ // Вводим заранее заготовленный комментарий для курьера
        commentField.sendKeys("Некий коммент");
    }
    @FindBy(how = How.XPATH, using = "//button[text()='Да']")
    public SelenideElement confirmationButton; // кнопка подтверждения
    public void confirmOrder(){
        confirmationButton.shouldBe(Condition.visible);
        confirmationButton.click();
    }
    @FindBy(how = How.XPATH, using = "//div[@class='Order_ModalHeader__3FDaJ']")
    public SelenideElement orderConfirmed; // Заказ подтвержден


    public void isOrderConfirmed(){

        orderConfirmed.shouldBe(Condition.visible);
        orderConfirmed.getText().contains("Заказ оформлен");

    }



}
