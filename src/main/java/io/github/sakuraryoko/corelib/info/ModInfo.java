package io.github.sakuraryoko.corelib.info;

import eu.pb4.placeholders.api.TextParserUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.ContactInformation;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.fabricmc.loader.api.metadata.Person;
import net.minecraft.text.Text;

import java.util.*;

public class ModInfo {
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

    protected ModInfo(String modID) {
        if (modID.isEmpty())
            return;
        if (FabricLoader.getInstance().getModContainer("minecraft").isPresent())
            this.mcVersion = FabricLoader.getInstance().getModContainer("minecraft").get().getMetadata().getVersion().getFriendlyString();
        else
            // Apparently, this isn't Minecraft?
            return;
        this.instance = FabricLoader.getInstance();
        this.MOD_ID = modID;
        this.envType = this.instance.getEnvironmentType();
        if (this.instance.getModContainer(this.MOD_ID).isPresent()) {
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
            else {
                StringBuilder authoString = new StringBuilder();
                final Iterator<Person> personIterator = this.authors.iterator();
                while (personIterator.hasNext()) {
                    if (authoString.isEmpty())
                        authoString = new StringBuilder(personIterator.next().getName());
                    else
                        authoString.append(", ").append(personIterator.next().getName());
                }
                this.authorString = authoString.toString();
            }
            if (this.contrib.isEmpty())
                this.contribString = "";
            else {
                StringBuilder contribStr = new StringBuilder();
                final Iterator<Person> personIterator = this.contrib.iterator();
                while (personIterator.hasNext()) {
                    if (contribStr.isEmpty())
                        contribStr = new StringBuilder(personIterator.next().getName());
                    else
                        contribStr.append(", ").append(personIterator.next().getName());
                }
                this.contribString = contribStr.toString();
            }
            if (this.licenses.isEmpty())
                this.licenseString = "";
            else {
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
    protected String getMCVersion() { return this.mcVersion; }

    protected String getModID() { return this.MOD_ID; }

    protected FabricLoader getModInstance() { return this.instance; }

    protected ModContainer getModContainer() { return this.modContainer; }

    protected ModMetadata getModMetadata() { return this.modMetadata; }

    protected EnvType getModEnv() { return this.envType; }

    protected boolean isClient() { return this.envType == EnvType.CLIENT; }
    protected boolean isServer() { return this.envType == EnvType.SERVER; }
    protected String getModVersion() { return this.modVersion; }

    protected String getModName() { return this.modName; }

    protected String getModDesc() { return this.description; }

    protected Collection<Person> getModAuthors() { return this.authors; }

    protected Collection<Person> getModContrib() { return this.contrib; }

    protected ContactInformation getModContacts() { return this.contacts; }

    protected Collection<String> getModLicense() { return this.licenses; }

    protected String getModAuthor$String() { return this.authorString; }

    protected String getModContrib$String() { return this.contribString; }

    protected String getModLicense$String() { return this.licenseString; }

    protected String getModHomepage() { return this.homepage; }

    protected String getModSources() { return this.source; }

    protected String getModIssues() { return this.issues; }

    protected Map<String, String> getModBasicInfo() {
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

    protected Map<String, Text> getModFormattedInfo() {
        Map <String, Text> fmtInfo = new HashMap<>();
        fmtInfo.put("ver",  Text.of(this.modName+"-"+this.mcVersion+"-"+this.modVersion));
        fmtInfo.put("auth", TextParserUtils.formatText("Author: <pink>"+this.authorString+"</pink>"));
        fmtInfo.put("con",  TextParserUtils.formatText("Contrib: <lime>"+this.contribString+"</lime>"));
        fmtInfo.put("lic",  TextParserUtils.formatText("License: <yellow>"+this.licenseString+"</yellow>"));
        fmtInfo.put("home", TextParserUtils.formatText("Homepage: <cyan><url:'"+this.homepage+"'>"+this.homepage+"</url></cyan>"));
        fmtInfo.put("src",  TextParserUtils.formatText("Source: <cyan><url:'"+this.source+"'>"+this.source+"</url></cyan>"));
        fmtInfo.put("iss",  TextParserUtils.formatText("Issues: <cyan><url:'"+this.issues+"'>"+this.issues+"</url></cyan>"));
        fmtInfo.put("desc", TextParserUtils.formatText("Description: <light_blue>"+this.description+"</light_blue>"));
        return fmtInfo;
    }
}
