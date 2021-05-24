package dbconverter.params;

/**
 * Class responsible for parsing the command line parameteres
 *
 * @author Anderson Spaniol
 */
public class Parameters {

    private String sourceDbms = "";
    private String sourceHostname = "";
    private String sourcePort = "";
    private String sourceDatabase = "";
    private String sourceSchema = "";
    private String sourceUser = "";
    private String sourcePassword = "";
    private String targetDbms = "";
    private String targetHostname = "";
    private String targetPort = "";
    private String targetDatabase = "";
    private String targetSchema = "";
    private String targetUser = "";
    private String targetPassword = "";

    private Parameters() {
    }

    public void parse(String... args) throws ParametersParsingException {
        try {
            for (int i = 0; i < args.length; i++) {
                String arg = args[i];
                if (arg.startsWith("--source-dbms=")) {
                    sourceDbms = arg.split("=")[1];
                }
                if (arg.startsWith("--source-hostname=")) {
                    sourceHostname = arg.split("=")[1];
                }
                if (arg.startsWith("--source-port=")) {
                    sourcePort = arg.split("=")[1];
                }
                if (arg.startsWith("--source-database=")) {
                    sourceDatabase = arg.split("=")[1];
                }
                if (arg.startsWith("--source-schema=")) {
                    sourceSchema = arg.split("=")[1];
                }
                if (arg.startsWith("--source-user=")) {
                    sourceUser = arg.split("=")[1];
                }
                if (arg.startsWith("--source-password=")) {
                    sourcePassword = arg.split("=")[1];
                }
                if (arg.startsWith("--target-dbms=")) {
                    targetDbms = arg.split("=")[1];
                }
                if (arg.startsWith("--target-hostname=")) {
                    targetHostname = arg.split("=")[1];
                }
                if (arg.startsWith("--target-port=")) {
                    targetPort = arg.split("=")[1];
                }
                if (arg.startsWith("--target-database=")) {
                    targetDatabase = arg.split("=")[1];
                }
                if (arg.startsWith("--target-schema=")) {
                    targetSchema = arg.split("=")[1];
                }
                if (arg.startsWith("--target-user=")) {
                    targetUser = arg.split("=")[1];
                }
                if (arg.startsWith("--target-passsword=")) {
                    targetPassword = arg.split("=")[1];
                }
            }
        } catch (Exception e) {
            throw new ParametersParsingException("Exception parsing parameters!", e);
        }

    }

    public String getSourceDbms() {
        return sourceDbms;
    }

    public String getSourceHostname() {
        return sourceHostname;
    }

    public String getSourcePort() {
        return sourcePort;
    }

    public String getSourceDatabase() {
        return sourceDatabase;
    }

    public String getSourceSchema() {
        return sourceSchema;
    }

    public String getSourceUser() {
        return sourceUser;
    }

    public String getSourcePassword() {
        return sourcePassword;
    }

    public String getTargetDbms() {
        return targetDbms;
    }

    public String getTargetHostname() {
        return targetHostname;
    }

    public String getTargetPort() {
        return targetPort;
    }

    public String getTargetDatabase() {
        return targetDatabase;
    }

    public String getTargetSchema() {
        return targetSchema;
    }

    public String getTargetUser() {
        return targetUser;
    }

    public String getTargetPassword() {
        return targetPassword;
    }

    public static Parameters def() {
        return ParametersHolder.INSTANCE;
    }

    private static class ParametersHolder {

        private static final Parameters INSTANCE = new Parameters();
    }
}
