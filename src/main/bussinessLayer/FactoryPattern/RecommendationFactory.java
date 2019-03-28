package main.bussinessLayer.FactoryPattern;

public class RecommendationFactory {
    public Recommendation listRecommendation(String type)
    {
        if(type.equalsIgnoreCase("SF"))
            return new SFRecommendation();
        if(type.equalsIgnoreCase("Crime"))
            return new CrimeRecommendation();
        if(type.equalsIgnoreCase("History"))
            return new HistoryRecommendation();
        if(type.equalsIgnoreCase("Romance"))
            return new RomanceRecommendation();
        if(type.equalsIgnoreCase("Science"))
            return new ScienceRecommendation();
        return null;
    }
}
