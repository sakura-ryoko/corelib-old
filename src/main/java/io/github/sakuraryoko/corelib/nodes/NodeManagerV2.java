package io.github.sakuraryoko.corelib.nodes;

import eu.pb4.placeholders.api.node.TextNode;
import eu.pb4.placeholders.api.node.parent.ColorNode;
import eu.pb4.placeholders.api.parsers.TextParserV1;
import eu.pb4.placeholders.impl.textparser.TextParserImpl;
import io.github.sakuraryoko.corelib.config.nodes.MoreColorConfig;
import net.minecraft.text.TextColor;

import java.util.Iterator;
import java.util.List;

public class NodeManagerV2 {
       private static void registerColors() {
        final Iterator<MoreColorNode> iterator = MoreColorConfig.COLORS.iterator();
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
