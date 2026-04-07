package com.billion_dollor_company.npciServer.common.constants;

public class Constants {

    public static class Keys {

        public static final String NPCI_PUBLIC_KEY =
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtzlJDxZyYybLotiDimedygowDUGf7x6suqbpULbkGk/YtI+q+6yRWPhYnNeSiBrxgR7xev7Afz7a38FvZbXyUlriPqk0trzacWEyEKbJAogTtc/uKdD4tZ/8FI7qEVpP4SCUjbHxhigQzHR7rfYbzjMMRghk+CS4f0WdJ2LuHBxaIOTYFiaLu/JOKxRbFhStUwG6bbuaBzEdyPIWzt9DnotemcTVpmkgblF1W1dQ3uPf2yRCXQf5YEfvQUxYTQ36tKj9yclyLCgn/8o02SiyBRmRQ1mvbV1k4pLibZByfRWh6/xbPEGQFyqRpQT6FI28rWPh6K9Ehn7gXtjjsRgjcwIDAQAB";

        public static final String NPCI_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC3OUkPFnJjJsui2IOKZ53KCjANQZ/vHqy6pulQtuQaT9i0j6r7rJFY+Fic15KIGvGBHvF6/sB/PtrfwW9ltfJSWuI+qTS2vNpxYTIQpskCiBO1z+4p0Pi1n/wUjuoRWk/hIJSNsfGGKBDMdHut9hvOMwxGCGT4JLh/RZ0nYu4cHFog5NgWJou78k4rFFsWFK1TAbptu5oHMR3I8hbO30Oei16ZxNWmaSBuUXVbV1De49/bJEJdB/lgR+9BTFhNDfq0qP3JyXIsKCf/yjTZKLIFGZFDWa9tXWTikuJtkHJ9FaHr/Fs8QZAXKpGlBPoUjbytY+Hor0SGfuBe2OOxGCNzAgMBAAECggEAHFwDCWai342b26xMi+nRe/oLXvnnMeFFwKw2FRashBVoyDsr2Ww9S7qWKKHwncgh1Pz/noHQ32vQqpfWULVn+O71Rtf01KCy5PVeL5fz0+77sgVUP+exn0HQiDQTWbU+A5gB4P6yZglMiauGkIdf3WYnAlipRtk+2gA3PLyXeK/NVKoOfr74nWXK/6CO5DO90f7uGVEc6ZwnTq7koyZgtYuDGEyAMMpkUIGfHB5WBE1TSq8ZQrrzhmWqybW2R2Cp5hpzRsvMSLtEovQaEVmMQBP9w/376m0V9qyoMeZ4qyVtNZQTRlKQghq1ESt06+rSqm9kzZJqLVDDrH+wAtHaCQKBgQDwj88NxPnAGpCGoPrLxKMxOdpIR00IBczegIQOuWRy+kAkXFZiFYwkHM/FJcH08+l3E6N98pL/mh0L+CU9YxlDHgBX4ZazJiIdl0tm0d3DHF1plwI3Y0jU7cMUvfHKwCjwwkgP8fbBP01HFh06n902kUwzxadLQMQRMnlL4qftiQKBgQDC+3hpmerUU+6RFHs47fOSkwExT1194KQE3QZC88zyvSneepDQ6yrl5MddYS1yS6CUey0qttPCapTv/uWmJxGcWd3b7/x1mICIJEtya1vV1lGIVegzhe5PXTBfOOEvyJTopGMFo34FuJYMC4iOR822a2K7uCwSvCOX9fL37PHmGwKBgQC6Jf4J4KeGUMgPdZg8f7/sQqE3lvzzvVFHx9job+tb0Ww69rzdj2DvPOBF5S7ONsQtIaQ8vK2lXoMllPJ0KzAMMLYBSw5LOLHjhgSR3sokxdk+8NyFMa3VHtr7PPRSeRn1G9GZjRntded9n44kpDoQlKSL12HaKJbP2+fiodRiSQKBgDwEhgsTarpS35Ne15Mtg5UxlONlx6Rwmmw40K2ZOBPb8GBgH+V/DeRX6JowyHlvGA28YKnOPx6QCipZrrzXN8+FxgnmNDLKb334lhSxQUJwRwaNUv1RE4rwZ2xYKNC5kESQHT7mFWuTZvRsNGt/ZK3rHigkR8S4jsg+17xC/i7TAoGAWydVpuH1gXjwwFZPDNlezppRqO8GMiCjCcFBAnuEA8fuhPfblJNRbh/qRfpH3QtHl5n9+DOrPOQ9EDJcM/Q6i5rsnC+m2qh8kpI5vxNHcRo7oYnm9iHs/o5+SoiyAfwn1WKkMiaA3aEc2vvMJjRKb844riZgjcszzfkYRxR64tA=";


        // abhi k liye hardcoded rakha hai bank ki public key ko.
        // but isko ese nahi kar sakte. db me different banks ki public keys ko store karna padega fir request me aaega account holder k bank account ka naam.
        public static final String BANK_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA1qLZAgclRGd+oClwDD6xfAEpSiA01yxwfAjd7grUm5H1AcNgCU5/u+LVS+nn4uvfrcNG+RVYXIMpnB6ahbtBp909XxDPIYYDC4XLau6wGv5A+1owQRgURk9DvRUXrd913CslBiT16LdBaRNjU6EOy25EFV6yQLKNUJudhdCxf9ZmbBpvIha2EYlS4O9kU+ndy2Z05K5D35bxSNG7LlGLZ5WO0v3FAWpKUgx+XYtgbmNisueQ2cqpvQwILkB8RL2YCiNDgT0406FAtPcTiMkiY2Rzm2CH7KfscgfX20C245aLdGWJPE6MuBEJYtN1+Zj7Y3cjcUjof1uVseYswvmJiwIDAQAB";
    }



    public static class Status {

        public static final String SUCCESS = "Success";
        public static final String FAILED = "Failed";

    }

    public static class Values{
        public static final String ERROR_IN_CRYPTOGRAPHY = "Some error occurred. Probably the pin entered was wrong.";
        public static final String ERROR_IN_AES = "Some error occurred. Probably corrupted fields";
    }

    public static class MessagePrinter {
        public static class Server {
            public static final String NPCI = "NPCI";
        }
        public static class MethodType {
            public static final String InitiateTransaction = "InitiateTransaction";
            public static final String CheckBalance = "CheckBalance";
            public static final String Register = "Register";
            public static final String FetchKeys = "FetchKeys";
        }
    }



    public static class Servers {

        public static class BankServer {

            private static final String BANK_SERVER_PORT = "16000";

            private static final String BASE_URL = "http://localhost:" + BANK_SERVER_PORT + "/bank";

            private static final String START_TRANSACTION = "/transaction";
            private static final String CHECK_BALANCE = "/checkBalance";

            public static String getTransactionURL() {
                return BASE_URL + START_TRANSACTION;
            }

            public static String getCheckBalanceURL(){
                return BASE_URL + CHECK_BALANCE;
            }
        }

    }
}
