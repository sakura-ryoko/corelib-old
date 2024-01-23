package io.github.sakuraryoko.corelib.network;

import net.minecraft.util.Identifier;

public class PayloadType
{
    protected String name;
    protected String path;
    protected String type;
    protected Identifier identifier;
    public PayloadType(String name, String path, String type)
    {
        this.name = name;
        this.path = path;
        this.type = type;
        this.identifier = new Identifier(name, path);
    }
    public Identifier getId() { return this.identifier; }
    public String getType() { return this.type; }
}
