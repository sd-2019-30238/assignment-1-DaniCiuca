package main.bussinessLayer.FactoryPattern;

import main.databaseLayer.DAO.DAOBook;

public class CrimeRecommendation implements Recommendation {

    @Override
    public String getRecommendation() {
        DAOBook daoBook = new DAOBook();
        return daoBook.filterByGenre("Crime");
    }
}
