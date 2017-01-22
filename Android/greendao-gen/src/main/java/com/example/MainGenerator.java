package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;

public class MainGenerator {

    private static final String PROJECT_DIR = System.getProperty("user.dir");

    public static void main(String[] args) {
        Schema schema = new Schema(1, "com.elock.tylerphelps.elock");
        schema.enableKeepSectionsByDefault();

        addTables(schema);

        try {
            new DaoGenerator().generateAll(schema, PROJECT_DIR + "/app/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addTables(final Schema schema) {
        Entity lock = addLock(schema);
    }

    private static Entity addLock(final Schema schema) {
        Entity lock = schema.addEntity("Lock");
        lock.addIdProperty().primaryKey().autoincrement();
        lock.addStringProperty("identifier").notNull();
        lock.addStringProperty("channel").notNull();
        lock.addStringProperty("publishKey").notNull();
        lock.addStringProperty("subscribeKey").notNull();
        lock.addStringProperty("nickname").notNull();
        return lock;
    }
}