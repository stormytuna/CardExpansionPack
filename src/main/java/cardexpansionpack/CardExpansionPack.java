package cardexpansionpack;

import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.interfaces.AddAudioSubscriber;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditKeywordsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import basemod.interfaces.PostInitializeSubscriber;
import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.cards.blue.*;
import cardexpansionpack.cards.colorless.*;
import cardexpansionpack.cards.green.*;
import cardexpansionpack.cards.purple.*;
import cardexpansionpack.cards.red.*;
import cardexpansionpack.util.GeneralUtils;
import cardexpansionpack.util.KeywordInfo;
import cardexpansionpack.util.Sounds;
import cardexpansionpack.util.TextureLoader;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglFileHandle;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.evacipated.cardcrawl.modthespire.ModInfo;
import com.evacipated.cardcrawl.modthespire.Patcher;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardGroup.CardGroupType;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.neow.NeowRoom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.scannotation.AnnotationDB;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;
import java.text.Bidi;
import java.util.*;

@SpireInitializer
public class CardExpansionPack implements
        EditCardsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        AddAudioSubscriber,
        PostInitializeSubscriber {
    public static ModInfo info;
    public static String modID; //Edit your pom.xml to change this
    static { loadModInfo(); }
    private static final String resourcesFolder = checkResourcesPath();
    public static final Logger logger = LogManager.getLogger(modID); //Used to output to the console.

    //This is used to prefix the IDs of various objects like cards and relics,
    //to avoid conflicts between different mods using the same name for things.
    public static String makeID(String id) {
        return modID + ":" + id;
    }

    //This will be called by ModTheSpire because of the @SpireInitializer annotation at the top of the class.
    public static void initialize() {
        new CardExpansionPack();
    }

    public CardExpansionPack() {
        BaseMod.subscribe(this); //This will make BaseMod trigger all the subscribers at their appropriate times.
        logger.info(modID + " subscribed to BaseMod.");
    }

    @Override
    public void receivePostInitialize() {
        //This loads the image used as an icon in the in-game mods menu.
        Texture badgeTexture = TextureLoader.getTexture(imagePath("badge.png"));
        //Set up the mod information displayed in the in-game mods menu.
        //The information used is taken from your pom.xml file.

        //If you want to set up a config panel, that will be done here.
        //You can find information about this on the BaseMod wiki page "Mod Config and Panel".
        BaseMod.registerModBadge(badgeTexture, info.Name, GeneralUtils.arrToString(info.Authors), info.Description, null);
    }

    /*----------Localization----------*/

    //This is used to load the appropriate localization files based on language.
    private static String getLangString()
    {
        return Settings.language.name().toLowerCase();
    }
    private static final String defaultLanguage = "eng";

    public static final Map<String, KeywordInfo> keywords = new HashMap<>();

    @Override
    public void receiveEditStrings() {
        /*
            First, load the default localization.
            Then, if the current language is different, attempt to load localization for that language.
            This results in the default localization being used for anything that might be missing.
            The same process is used to load keywords slightly below.
        */
        loadLocalization(defaultLanguage); //no exception catching for default localization; you better have at least one that works.
        if (!defaultLanguage.equals(getLangString())) {
            try {
                loadLocalization(getLangString());
            }
            catch (GdxRuntimeException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadLocalization(String lang) {
        BaseMod.loadCustomStringsFile(CardStrings.class, localizationPath(lang, "CardStrings.json"));
        BaseMod.loadCustomStringsFile(PowerStrings.class, localizationPath(lang, "PowerStrings.json"));
        BaseMod.loadCustomStringsFile(UIStrings.class, localizationPath(lang, "UIStrings.json"));
    }

    @Override
    public void receiveEditCards() {
        new AutoAdd(modID)
            .packageFilter(BaseCard.class)
            .setDefaultSeen(true)
            .cards();
    }

    @Override
    public void receiveAddAudio() {
        loadAudio(Sounds.class);
    }

    private static final String[] AUDIO_EXTENSIONS = { ".ogg", ".wav", ".mp3" }; //There are more valid types, but not really worth checking them all here
    private void loadAudio(Class<?> cls) {
        try {
            Field[] fields = cls.getDeclaredFields();
            outer:
            for (Field f : fields) {
                int modifiers = f.getModifiers();
                if (Modifier.isStatic(modifiers) && Modifier.isPublic(modifiers) && f.getType().equals(String.class)) {
                    String s = (String) f.get(null);
                    if (s == null) { //If no defined value, determine path using field name
                        s = audioPath(f.getName());

                        for (String ext : AUDIO_EXTENSIONS) {
                            String testPath = s + ext;
                            if (Gdx.files.internal(testPath).exists()) {
                                s = testPath;
                                BaseMod.addAudio(s, s);
                                f.set(null, s);
                                continue outer;
                            }
                        }
                        throw new Exception("Failed to find an audio file \"" + f.getName() + "\" in " + resourcesFolder + "/audio; check to ensure the capitalization and filename are correct.");
                    }
                    else { //Otherwise, load defined path
                        if (Gdx.files.internal(s).exists()) {
                            BaseMod.addAudio(s, s);
                        }
                        else {
                            throw new Exception("Failed to find audio file \"" + s + "\"; check to ensure this is the correct filepath.");
                        }
                    }
                }
            }
        }
        catch (Exception e) {
            logger.error("Exception occurred in loadAudio: ", e);
        }
    }

    //These methods are used to generate the correct filepaths to various parts of the resources folder.
    public static String localizationPath(String lang, String file) {
        return resourcesFolder + "/localization/" + lang + "/" + file;
    }

    public static String audioPath(String file) {
        return resourcesFolder + "/audio/" + file;
    }
    public static String imagePath(String file) {
        return resourcesFolder + "/images/" + file;
    }
    public static String characterPath(String file) {
        return resourcesFolder + "/images/character/" + file;
    }
    public static String powerPath(String file) {
        return resourcesFolder + "/images/powers/" + file;
    }
    public static String relicPath(String file) {
        return resourcesFolder + "/images/relics/" + file;
    }

    /**
     * Checks the expected resources path based on the package name.
     */
    private static String checkResourcesPath() {
        String name = CardExpansionPack.class.getName(); //getPackage can be iffy with patching, so class name is used instead.
        int separator = name.indexOf('.');
        if (separator > 0)
            name = name.substring(0, separator);

        FileHandle resources = new LwjglFileHandle(name, Files.FileType.Internal);

        if (!resources.exists()) {
            throw new RuntimeException("\n\tFailed to find resources folder; expected it to be at  \"resources/" + name + "\"." +
                    " Either make sure the folder under resources has the same name as your mod's package, or change the line\n" +
                    "\t\"private static final String resourcesFolder = checkResourcesPath();\"\n" +
                    "\tat the top of the " + CardExpansionPack.class.getSimpleName() + " java file.");
        }
        if (!resources.child("images").exists()) {
            throw new RuntimeException("\n\tFailed to find the 'images' folder in the mod's 'resources/" + name + "' folder; Make sure the " +
                    "images folder is in the correct location.");
        }
        if (!resources.child("localization").exists()) {
            throw new RuntimeException("\n\tFailed to find the 'localization' folder in the mod's 'resources/" + name + "' folder; Make sure the " +
                    "localization folder is in the correct location.");
        }

        return name;
    }

    /**
     * This determines the mod's ID based on information stored by ModTheSpire.
     */
    private static void loadModInfo() {
        Optional<ModInfo> infos = Arrays.stream(Loader.MODINFOS).filter((modInfo)->{
            AnnotationDB annotationDB = Patcher.annotationDBMap.get(modInfo.jarURL);
            if (annotationDB == null)
                return false;
            Set<String> initializers = annotationDB.getAnnotationIndex().getOrDefault(SpireInitializer.class.getName(), Collections.emptySet());
            return initializers.contains(CardExpansionPack.class.getName());
        }).findFirst();
        if (infos.isPresent()) {
            info = infos.get();
            modID = info.ID;
        }
        else {
            throw new RuntimeException("Failed to determine mod info/ID based on initializer.");
        }
    }

    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String json = Gdx.files.internal(localizationPath(defaultLanguage, "Keywords.json")).readString(String.valueOf(StandardCharsets.UTF_8));
        KeywordInfo[] keywords = gson.fromJson(json, KeywordInfo[].class);
        for (KeywordInfo keyword : keywords) {
            keyword.prep();
            registerKeyword(keyword);
        }

        if (!defaultLanguage.equals(getLangString())) {
            try
            {
                json = Gdx.files.internal(localizationPath(getLangString(), "Keywords.json")).readString(String.valueOf(StandardCharsets.UTF_8));
                keywords = gson.fromJson(json, KeywordInfo[].class);
                for (KeywordInfo keyword : keywords) {
                    keyword.prep();
                    registerKeyword(keyword);
                }
            }
            catch (Exception e)
            {
                logger.warn(modID + " does not support " + getLangString() + " keywords.");
            }
        }
    }

    private void registerKeyword(KeywordInfo info) {
        BaseMod.addKeyword(modID.toLowerCase(), info.PROPER_NAME, info.NAMES, info.DESCRIPTION, info.COLOR);
        if (!info.ID.isEmpty())
        {
            keywords.put(info.ID, info);
        }
    }

    @SpirePatch2(clz = NeowRoom.class, method = "update")
    public static class SpawnCardsForShowcase {
        @SpirePostfixPatch
        public static void patch() {
            if (true) {
                return;
            }

            ArrayList<AbstractCard> cards = new ArrayList<>();
            
            addSelfAndUpgradedCopy(new Beatdown(), cards);
            addSelfAndUpgradedCopy(new Relentless(), cards);
            addSelfAndUpgradedCopy(new SanguineShield(), cards);

            addSelfAndUpgradedCopy(new BloodPact(), cards);
            addSelfAndUpgradedCopy(new Calloused(), cards);
            addSelfAndUpgradedCopy(new HoneBlade(), cards);
            addSelfAndUpgradedCopy(new LashOut(), cards);
            addSelfAndUpgradedCopy(new Overpower(), cards);
            addSelfAndUpgradedCopy(new RecklessAbandon(), cards);
            addSelfAndUpgradedCopy(new SunderingStrike(), cards);
            addSelfAndUpgradedCopy(new Tenacity(), cards);

            addSelfAndUpgradedCopy(new FiendishVigor(), cards);
            addSelfAndUpgradedCopy(new LashOut(), cards);
            addSelfAndUpgradedCopy(new Mania(), cards);
            addSelfAndUpgradedCopy(new Pyre(), cards);

            addSelfAndUpgradedCopy(new DuckAndWeave(), cards);
            addSelfAndUpgradedCopy(new Juke(), cards);
            addSelfAndUpgradedCopy(new PoisonDart(), cards);
            addSelfAndUpgradedCopy(new Polish(), cards);
            addSelfAndUpgradedCopy(new Smokescreen(), cards);

            addSelfAndUpgradedCopy(new BladeBarrier(), cards);
            addSelfAndUpgradedCopy(new Blindside(), cards);
            addSelfAndUpgradedCopy(new Feint(), cards);
            addSelfAndUpgradedCopy(new NerveGas(), cards);
            addSelfAndUpgradedCopy(new SneakAttack(), cards);
            addSelfAndUpgradedCopy(new ToxicSmog(), cards);
            addSelfAndUpgradedCopy(new Trace(), cards);

            addSelfAndUpgradedCopy(new Evasion(), cards);
            addSelfAndUpgradedCopy(new Trickshot(), cards);
            addSelfAndUpgradedCopy(new UnderhandedTactics(), cards);

            addSelfAndUpgradedCopy(new Dequeue(), cards);
            addSelfAndUpgradedCopy(new Malware(), cards);
            addSelfAndUpgradedCopy(new Ping(), cards);
            addSelfAndUpgradedCopy(new Polarization(), cards);

            addSelfAndUpgradedCopy(new Cryogenics(), cards);
            addSelfAndUpgradedCopy(new EventHorizon(), cards);
            addSelfAndUpgradedCopy(new Firewall(), cards);
            addSelfAndUpgradedCopy(new HardLightBarrier(), cards);
            addSelfAndUpgradedCopy(new Iteration(), cards);
            addSelfAndUpgradedCopy(new PowerCell(), cards);
            addSelfAndUpgradedCopy(new TrialAndError(), cards);
            addSelfAndUpgradedCopy(new TunnelVision(), cards);

            addSelfAndUpgradedCopy(new BetaTesting(), cards);
            addSelfAndUpgradedCopy(new HeadsUpDisplay(), cards);
            addSelfAndUpgradedCopy(new RNG(), cards);

            addSelfAndUpgradedCopy(new ForetellingStrike(), cards);
            addSelfAndUpgradedCopy(new GraspPotential(), cards);
            addSelfAndUpgradedCopy(new SplitReality(), cards);

            addSelfAndUpgradedCopy(new Counterattack(), cards);
            addSelfAndUpgradedCopy(new FollowThrough(), cards);
            addSelfAndUpgradedCopy(new Foreshadow(), cards);
            addSelfAndUpgradedCopy(new GlimpseFuture(), cards);
            addSelfAndUpgradedCopy(new Patience(), cards);
            addSelfAndUpgradedCopy(new PierceTheVeil(), cards);
            addSelfAndUpgradedCopy(new PlantFeet(), cards);
            addSelfAndUpgradedCopy(new OnSolidGround(), cards);
            addSelfAndUpgradedCopy(new Prediction(), cards);
            addSelfAndUpgradedCopy(new ShieldOfFaith(), cards);

            addSelfAndUpgradedCopy(new Condemn(), cards);
            addSelfAndUpgradedCopy(new EbbAndFlow(), cards);
            addSelfAndUpgradedCopy(new Suppress(), cards);

            addSelfAndUpgradedCopy(new Ambrosia(), cards);
            addSelfAndUpgradedCopy(new Bide(), cards);
            addSelfAndUpgradedCopy(new Salvage(), cards);
            addSelfAndUpgradedCopy(new Stockpile(), cards);
            addSelfAndUpgradedCopy(new Zeal(), cards);

            addSelfAndUpgradedCopy(new ArcaneWard(), cards);
            addSelfAndUpgradedCopy(new Dispel(), cards);
            addSelfAndUpgradedCopy(new HiddenCache(), cards);
            addSelfAndUpgradedCopy(new Jackpot(), cards);
            addSelfAndUpgradedCopy(new Radiance(), cards);

            CardGroup cardGroup = new CardGroup(CardGroupType.UNSPECIFIED);
            for (AbstractCard card : cards) {
                cardGroup.addToTop(card);
            }

            BaseMod.openCustomGridScreen(cardGroup, 1, "", x -> {});
        }

		private static void addSelfAndUpgradedCopy(AbstractCard card, ArrayList<AbstractCard> cards) {
			AbstractCard upgrade = card.makeCopy();
			upgrade.upgrade();

            cards.add(card);
			cards.add(upgrade);
		}
    }
}
