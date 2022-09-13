package pluginprovider.utils;

import me.devtec.shared.utility.StringUtils;

public class Processor {

    // ~Random(min,max)
    public static int parseItemAmount(String text) {
        text = text.replace("~Random(", "").replace(")", "");
        int min = Integer.parseInt(text.split(",")[0]);
        int max = Integer.parseInt(text.split(",")[1]);
        return StringUtils.generateRandomInt(min, max);
    }

}