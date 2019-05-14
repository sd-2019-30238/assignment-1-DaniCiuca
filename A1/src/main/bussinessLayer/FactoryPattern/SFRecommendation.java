package main.bussinessLayer.FactoryPattern;

import main.databaseLayer.DAO.DAOBook;

public class SFRecommendation implements Recommendation {

    @Override
    public String getRecommendation() {
        DAOBook daoBook = new DAOBook();
        return daoBook.filterByGenre("SF");
    }
}
