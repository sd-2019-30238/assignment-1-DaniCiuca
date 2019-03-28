package main.bussinessLayer.FactoryPattern;

import main.databaseLayer.DAO.DAOBook;

public class ScienceRecommendation implements Recommendation {

    @Override
    public String getRecommendation() {
        DAOBook daoBook = new DAOBook();
        return daoBook.filterByGenre("Science");
    }
}
