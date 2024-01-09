package io.github.sakuraryoko.corelib.nodes;

import eu.pb4.placeholders.api.node.TextNode;
import eu.pb4.placeholders.api.node.parent.ColorNode;
import eu.pb4.placeholders.api.parsers.TextParserV1;
import eu.pb4.placeholders.impl.textparser.TextParserImpl;
import net.minecraft.text.TextColor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NodeManagerV2 {
    public static List<MoreColorNode> COLORS = new ArrayList<>();

    private static void initColors() {
        COLORS.add(new MoreColorNode("brown", "#632C04"));
        COLORS.add(new MoreColorNode("cyan","#2D7C9D"));
        COLORS.add(new MoreColorNode("dark_brown","#421F05"));
        COLORS.add(new MoreColorNode("dark_pink","#DE8BB4"));
        COLORS.add(new MoreColorNode("light_blue","#82ACE7"));
        COLORS.add(new MoreColorNode("light_brown","#7A4621"));
        COLORS.add(new MoreColorNode("light_gray","#BABAC1", List.of("light_grey")));
        COLORS.add(new MoreColorNode("light_pink","#F7B4D6"));
        COLORS.add(new MoreColorNode("lime","#76C610"));
        COLORS.add(new MoreColorNode("magenta","#CB69C5"));
        //COLORS.add(new MoreColorNode("orange","#E69E34"));
        //COLORS.add(new MoreColorNode("pink","#EDA7CB"));
        COLORS.add(new MoreColorNode("purple","#A453CE"));
        COLORS.add(new MoreColorNode("salmon", "#FF91A4", List.of("pink_salmon")));
    }
    private static void registerColors() {
        final Iterator<MoreColorNode> iterator = COLORS.iterator();
        MoreColorNode iColorNode;
        while (iterator.hasNext()) {
            iColorNode = iterator.next();
            // DataResult checked at initialization
            TextColor finalIColorNode = iColorNode.getColor();
            if (iColorNode.getAliases() != null) {
                TextParserV1.registerDefault(
                        TextParserV1.TextTag.of(
                                iColorNode.getName(),
                                iColorNode.getAliases(),
                                "color",
                                true,
                                wrap((nodes, arg) -> new ColorNode(nodes, finalIColorNode))
                        )
                );
            } else {
                TextParserV1.registerDefault(
                        TextParserV1.TextTag.of(
                                iColorNode.getName(),
                                List.of(""),
                                "color",
                                true,
                                wrap((nodes, arg) -> new ColorNode(nodes, finalIColorNode))
                        )
                );
            }
        }
    }
    public static void initNodes() {
        initColors();
    }
    public static void registerNodes() {
        registerColors();
    }

    // Copied wrap() from TextTags.java
    private static TextParserV1.TagNodeBuilder wrap(Wrapper wrapper) {
        return (tag, data, input, handlers, endAt) -> {
            var out = TextParserImpl.recursiveParsing(input, handlers, endAt);
            return new TextParserV1.TagNodeValue(wrapper.wrap(out.nodes(), data), out.length());
        };
    }
    interface Wrapper {
        TextNode wrap(TextNode[] nodes, String arg);
    }
}
