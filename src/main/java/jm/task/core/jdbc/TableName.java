package jm.task.core.jdbc;

public class TableName {

    public static final String HIBERNATE = "Hibernate_User_table";
    public static final String JDBC      = "JDBC_table";

    public static final String ONE_to_one_BiDir   = "persons_OtOB";
    public static final String one_to_ONE_BiDir   = "passports_OtOB";


    public static final String ONE_to_one_Uni_Dir   = "persons_OtOU";
    public static final String one_to_ONE_Uni_Dir   = "passports_OtOU";

    public static final String ONE_to_MANY_1   = "persons_OtM1";
    public static final String MANY_to_ONE_1   = "phoneNumbers_OtM1";


    public static final String ONE_to_MANY_2   = "persons_OtM2";
    public static final String MANY_to_ONE_2   = "phoneNumbers_OtM2";

    public static final String MANY_to_many   = "persons_MtM";
    public static final String many_to_MANY   = "shops_MtM";
}
