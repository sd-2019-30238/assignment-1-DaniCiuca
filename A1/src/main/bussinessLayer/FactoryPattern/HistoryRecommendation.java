package main.bussinessLayer.FactoryPattern;

import main.databaseLayer.DAO.DAOBook;

public class HistoryRecommendation implements Recommendation {

    @Override
    public String getRecommendation() {
        DAOBook daoBook = new DAOBook();
        return daoBook.filterByGenre("History");
    }
}
