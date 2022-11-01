## Room Database
- If you want to clear the data when migrating from one version to another.
- If you don’t want to provide migrations and you specifically want your database to be cleared when you upgrade the version,
- call fallbackToDestructiveMigration in the database builder:
```
database = Room.databaseBuilder(context.getApplicationContext(),
                        UsersDatabase.class, "Sample.db")
                .fallbackToDestructiveMigration()
                .build();
```

## Want to apply migration from version 1 to version 3
```
database = Room.databaseBuilder(context.getApplicationContext(),
        UsersDatabase.class, "Sample.db")
        .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
        .build();
```

