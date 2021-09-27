package by.jazzteam.numbertotext.dao;

public class DAOHolder {
    private final DAO dao = new XlsxDAO();

    private static class InstanceHolder {
        public static final DAOHolder instance = new DAOHolder();
    }

    private DAOHolder() {
    }

    public static DAOHolder getInstance() {
        return InstanceHolder.instance;
    }

    public DAO getDAO() {
        return dao;
    }

}
