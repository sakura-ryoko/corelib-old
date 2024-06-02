package io.github.sakuraryoko.corelib.impl.init;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.ContactInformation;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.fabricmc.loader.api.metadata.Person;
import net.minecraft.MinecraftVersion;
import net.minecraft.text.Text;
import io.github.sakuraryoko.corelib.api.nodes.NodeParserV1;
import io.github.sakuraryoko.corelib.util.CoreLog;

public class ModInitData
{
    private String MOD_ID;
    private String mcVersion;
    private FabricLoader instance;
    private ModContainer modContainer;
    private ModMetadata modMetadata;
    private EnvType envType;
    private String modName;
    private String modVersion;
    private String description;
    private Collection<Person> authors;
    private Collection<Person> contrib;
    private ContactInformation contacts;
    private Collection<String> licenses;
    private String authorString;
    private String contribString;
    private String licenseString;
    private String homepage;
    private String source;
    private String issues;

    // Server mode
    private boolean integratedServer;
    private boolean dedicatedServer;
    private boolean openToLan;

    public ModInitData(String modID)
    {
        if (modID.isEmpty())
            return;

        this.mcVersion = MinecraftVersion.CURRENT.getName();
        this.instance = FabricLoader.getInstance();
        this.MOD_ID = modID;
        this.envType = this.instance.getEnvironmentType();
        this.integratedServer = false;
        this.dedicatedServer = false;
        this.openToLan = false;

        if (this.instance.getModContainer(this.MOD_ID).isPresent())
        {
            this.modContainer = this.instance.getModContainer(this.MOD_ID).get();
            this.modMetadata = this.modContainer.getMetadata();
            this.modVersion = this.modMetadata.getVersion().getFriendlyString();
            this.modName = this.modMetadata.getName();
            this.description = this.modMetadata.getDescription();
            this.authors = this.modMetadata.getAuthors();
            this.contrib = this.modMetadata.getContributors();
            this.contacts = this.modMetadata.getContact();
            this.licenses = this.modMetadata.getLicense();
            this.homepage = this.contacts.asMap().get("homepage");
            this.source = this.contacts.asMap().get("sources");
            this.issues = this.contacts.asMap().get("issues");

            if (this.authors.isEmpty())
                this.authorString = "";
            else
            {
                StringBuilder authoString = new StringBuilder();
                final Iterator<Person> personIterator = this.authors.iterator();
                while (personIterator.hasNext())
                {
                    if (authoString.isEmpty())
                        authoString = new StringBuilder(personIterator.next().getName());
                    else
                        authoString.append(", ").append(personIterator.next().getName());
                }
                this.authorString = authoString.toString();
            }
            if (this.contrib.isEmpty())
                this.contribString = "";
            else
            {
                StringBuilder contribStr = new StringBuilder();
                final Iterator<Person> personIterator = this.contrib.iterator();
                while (personIterator.hasNext())
                {
                    if (contribStr.isEmpty())
                        contribStr = new StringBuilder(personIterator.next().getName());
                    else
                        contribStr.append(", ").append(personIterator.next().getName());
                }
                this.contribString = contribStr.toString();
            }
            if (this.licenses.isEmpty())
                this.licenseString = "";
            else
            {
                StringBuilder licString = new StringBuilder();
                final Iterator<String> stringIterator = this.licenses.iterator();
                while (stringIterator.hasNext()) {
                    if (licString.isEmpty())
                        licString = new StringBuilder(stringIterator.next());
                    else
                        licString.append(", ").append(stringIterator.next());
                }
                this.licenseString = licString.toString();
            }
        }
    }

    public String getMCVersion() { return this.mcVersion; }

    public String getModID() { return this.MOD_ID; }

    public FabricLoader getModInstance() { return this.instance; }

    public ModContainer getModContainer() { return this.modContainer; }

    public ModMetadata getModMetadata() { return this.modMetadata; }

    public EnvType getModEnv() { return this.envType; }

    public boolean isClient() { return this.envType == EnvType.CLIENT; }

    public boolean isServer() { return this.envType == EnvType.SERVER; }

    public boolean isIntegratedServer() { return this.integratedServer; }

    public boolean isDedicatedServer() { return this.dedicatedServer; }

    public boolean isOpenToLan() { return this.openToLan; }

    public void setIntegratedServer(boolean toggle)
    {
        this.integratedServer = this.isClient() && toggle;
    }

    public void setDedicatedServer(boolean toggle)
    {
        this.dedicatedServer = this.isServer() && toggle;
    }

    public void setOpenToLan(boolean toggle)
    {
        if (this.isClient() && toggle)
        {
            this.openToLan = true;
            this.integratedServer = true;
        }
        else
        {
            this.openToLan = false;
        }
    }

    public String getModVersion() { return this.modVersion; }

    public String getModName() { return this.modName; }

    public String getModDesc() { return this.description; }

    public Collection<Person> getModAuthors() { return this.authors; }

    public Collection<Person> getModContrib() { return this.contrib; }

    public ContactInformation getModContacts() { return this.contacts; }

    public Collection<String> getModLicense() { return this.licenses; }

    public String getModAuthor$String() { return this.authorString; }

    public String getModContrib$String() { return this.contribString; }

    public String getModLicense$String() { return this.licenseString; }

    public String getModHomepage() { return this.homepage; }

    public String getModSources() { return this.source; }

    public String getModIssues() { return this.issues; }

    public Map<String, String> getModBasicInfo()
    {
        Map<String, String> basicInfo = new HashMap<>();

        basicInfo.put("ver",  this.modName+"-"+this.mcVersion+"-"+this.modVersion);
        basicInfo.put("auth", "Author: "+this.authorString);
        basicInfo.put("con",  "Contrib: "+this.contribString);
        basicInfo.put("lic",  "License: "+this.licenseString);
        basicInfo.put("home", "Homepage: "+this.homepage);
        basicInfo.put("src",  "Source: "+this.source);
        basicInfo.put("iss",  "Issues: "+this.issues);
        basicInfo.put("desc", "Description: "+this.description);

        return basicInfo;
    }

    public Map<String, Text> getModFormattedInfo()
    {
        Map <String, Text> fmtInfo = new HashMap<>();

        fmtInfo.put("ver",  Text.of(this.modName+"-"+this.mcVersion+"-"+this.modVersion));
        fmtInfo.put("auth", NodeParserV1.parse("Author: <pink>"+this.authorString+"</pink>"));
        fmtInfo.put("con",  NodeParserV1.parse("Contrib: <lime>"+this.contribString+"</lime>"));
        fmtInfo.put("lic",  NodeParserV1.parse("License: <yellow>"+this.licenseString+"</yellow>"));
        fmtInfo.put("home", NodeParserV1.parse("Homepage: <cyan><url:'"+this.homepage+"'>"+this.homepage+"</url></cyan>"));
        fmtInfo.put("src",  NodeParserV1.parse("Source: <cyan><url:'"+this.source+"'>"+this.source+"</url></cyan>"));
        fmtInfo.put("iss",  NodeParserV1.parse("Issues: <cyan><url:'"+this.issues+"'>"+this.issues+"</url></cyan>"));
        fmtInfo.put("desc", NodeParserV1.parse("Description: <light_blue>"+this.description+"</light_blue>"));

        return fmtInfo;
    }

    public void reset()
    {
        CoreLog.debug("ModInitData: reset()");
        this.integratedServer = false;
        this.openToLan = false;
        this.dedicatedServer = false;
    }
}
