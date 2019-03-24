public class PaymentMethod {
    private String type;
    private int cost;

    public PaymentMethod(String type)
    {
        if(type.equalsIgnoreCase("Day"))
        {
            this.type="Day";
            this.cost=2;
        }
        else
            if(type.equalsIgnoreCase("Week"))
            {
                this.type="Week";
                this.cost=10;
            }
            else
                if(type.equalsIgnoreCase("Month"))
                {
                    this.type="Month";
                    this.cost=30;
                }
                else
                    if(type.equalsIgnoreCase("Year"))
                    {
                        this.type="Year";
                        this.cost=300;
                    }
                    else
                    {
                        this.type="Unknown";
                        this.cost=0;
                    }
    }
    public void info()
    {
        System.out.println("Payment type: "+this.type+"\nCost: "+this.cost);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
