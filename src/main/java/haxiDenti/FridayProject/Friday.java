package haxiDenti.FridayProject;

import spark.Service;
import spark.utils.StringUtils;

public class Friday {
    private String shortenerName;
    private String userAPIKey;
    private String protocolPrefix;
    private FridayList list = new FridayList();
    private Service sparkService;
    private static final String defaultURLStepExecPath = "/friday/exec/";
    private String hostNameAndPort;

    public Friday(Service sparkService, String shortenerName, String userAPIKey, String protocolPrefix) {
        this.sparkService = sparkService;
        this.shortenerName = shortenerName;
        this.userAPIKey = userAPIKey;
        this.protocolPrefix = protocolPrefix;
    }

    public Friday(Service sparkService, String shortenerName, String userAPIKey) {
        this(sparkService, shortenerName, userAPIKey, "http");
    }

    public String link(String link) {
        return protocolPrefix + "://" + shortenerName + "/" + userAPIKey + "/" + stripProtocol(link);
    }

    public String step( boolean useFridayWrapper, FridayHandler handler) {
        String stepName = list.generateStep(handler);
        String locationToGo = hostNameAndPort + defaultURLStepExecPath + stepName;
        if (useFridayWrapper) return link(linkRedirector(locationToGo));
        return link(locationToGo);
    }

    private String stripProtocol(String str) {
        if (str.startsWith("http://")) {
            return str.substring(7);
        } else if (str.startsWith("https://")) {
            return str.substring(8);
        } else if (str.startsWith("/")) {
            return str.substring(1);
        }
        return str;
    }

    public void init(String hostNameAndPort) {
        this.hostNameAndPort = hostNameAndPort;
        sparkService.get(defaultURLStepExecPath + ":step", (req, res)->{
            String stepName = req.params("step");
            if (StringUtils.isEmpty(stepName)) {
                return "incorrect";
            }
            Object object = list.executeStep(req, res, stepName);
            if (object != null) return object;
            return "Not exist!";
        });
    }

    private static String linkRedirector(String link) {
        return "aldienightstar.github.io/friday/index.html?redir=" + link;
    }

}
