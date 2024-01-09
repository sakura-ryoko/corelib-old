package io.github.sakuraryoko.corelib.info;

import eu.pb4.placeholders.api.TextParserUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.ContactInformation;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.fabricmc.loader.api.metadata.Person;
import net.minecraft.text.Text;

import java.util.Collection;
import java.util.Iterator;

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

    protected String getModBasicInfo(int level) {
        if (level < 0)
            return "";
        StringBuilder basicInfo = new StringBuilder(this.modName);
        basicInfo.append("-").append(this.mcVersion).append("-").append(this.modVersion);
        if (level > 0)
            basicInfo.append("\nAuthor: ").append(this.authorString);
        if (level > 1)
            basicInfo.append("\nLicense: ").append(this.licenseString);
        if (level > 2)
            basicInfo.append("\nHomepage: ").append(this.homepage);
        if (level > 3)
            basicInfo.append("\nDescription: ").append(this.description);
        return basicInfo.toString();
    }

    protected Text getModFormattedInfo(int level) {
        if (level < 0)
            return Text.of("");
        StringBuilder fmtInfo = new StringBuilder(this.modName);
        fmtInfo.append("-").append(this.mcVersion).append("-").append(this.modVersion);
        if (level > 0)
            fmtInfo.append("\nAuthor: <pink>").append(this.authorString).append("</pink>");
        if (level > 1)
            fmtInfo.append("\nLicense: <yellow>").append(this.licenseString).append("</yellow>");
        if (level > 2)
            fmtInfo.append("\nHomepage: <cyan><url:'").append(this.homepage).append("'>").append(this.homepage).append("</url></cyan>");
        if (level > 3)
            fmtInfo.append("\nSource: <cyan><url:'").append(this.source).append("'>").append(this.source).append("</url></cyan>");
        if (level > 4)
            fmtInfo.append("\nDescription: <light_blue>").append(this.description).append("</light_blue>");
        return TextParserUtils.formatText(fmtInfo.toString());
    }
}
