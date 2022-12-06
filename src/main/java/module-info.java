module fr.gsb.rv.dr{
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires org.mariadb.jdbc;

    opens fr.gsb.rv.dr to javafx.fxml;
    opens fr.gsb.rv.dr.entites to javafx.base;

    exports fr.gsb.rv.dr;
}