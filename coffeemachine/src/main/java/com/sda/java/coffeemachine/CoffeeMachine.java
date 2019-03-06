package com.sda.java.coffeemachine;

public class CoffeeMachine {
    private Stock stock = new Stock();
    private CoffeeType coffeeType = CoffeeType.FILTERCOFFEE;


    public static void main(String[] args) throws Exception {
        final CoffeeMachine coffeeMachine = new CoffeeMachine();
        Stock stock = coffeeMachine.getStock();
        stock.removeBeansFromStock(100);
        final Coffee coffee = coffeeMachine.prepareCoffee();
        System.out.println("Drinking the " + coffee);

    }

    public void setCoffeeType(CoffeeType coffeeType) {
        //TODO add check if credit is sufficient and show message
        this.coffeeType = coffeeType;
    }

    //detachable stock: can retrieve the stock and operate it independently

    public Stock getStock() {
        return stock;
    }

    public Coffee prepareCoffee() throws Exception {
        if (stock.getBeansStock() > coffeeType.getBeansRequired()) {
            stock.removeBeansFromStock(coffeeType.getBeansRequired());
            return createCoffee();
        }
        throw new Exception("Cannot make coffee");

    }

    private Coffee createCoffee() {
        switch (coffeeType) {
            case LATTE:
                return new Latte();
            case ESPRESSO:
                return new Espresso();
            case FILTERCOFFEE:
                return new FilterCoffee();
            default:
                return new FilterCoffee();
        }
    }
}
//addCredit
//returnCredit


