package cardexpansionpack.cards.purple;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import cardexpansionpack.CardExpansionPack;
import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.util.CardStats;

public class ForetellingStrike extends BaseCard {
    public static final String ID = makeID("ForetellingStrike");

    private static final CardStats INFO = new CardStats(
        CardColor.PURPLE,
        CardType.ATTACK,
        CardRarity.COMMON,
        CardTarget.ENEMY,
        2
    );

    private static final int SCRY = 2;
    private static final int SCRY_UPGRADE = 1;
    private static final int DAMAGE = 5;

    public ForetellingStrike() {
        super(ID, INFO);
        setMagic(SCRY, SCRY_UPGRADE);
        setDamage(DAMAGE);
        tags.add(CardTags.STRIKE);
    }

    @Override
    public AbstractCard makeCopy() {
        return new ForetellingStrike();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ScryAction(magicNumber));

        OnScryFinished.doForetellingStrikeDamage = true;
        OnScryFinished.discardSize = AbstractDungeon.player.discardPile.size();
        OnScryFinished.baseDamage = baseDamage;
        OnScryFinished.target = m;
    }

    // Quirk with how the action queue works, you can't wait for another action to be finished to continue what you want to do
    // So we are just patching scry action to also do damage if we played Foretelling Strike
    @SpirePatch2(clz = ScryAction.class, method = "update")
    public static class OnScryFinished {
        private static final AttackEffect[] POSSIBLE_ATTACK_EFFECTS = {
            AttackEffect.SLASH_DIAGONAL,
            AttackEffect.SLASH_HORIZONTAL,
            AttackEffect.SLASH_VERTICAL,
            AttackEffect.SLASH_HEAVY,
        };
        static boolean doForetellingStrikeDamage = false;
        static int discardSize = 0;
        static int baseDamage = 0;
        static AbstractMonster target;

        @SpirePostfixPatch
        public static void patch(ScryAction __instance) {
            CardExpansionPack.logger.info(__instance.isDone ? "DONE!!!" : "not done...");

            if (__instance.isDone && doForetellingStrikeDamage) {
                doForetellingStrikeDamage = false;
                    
                int newDiscardSize = AbstractDungeon.player.discardPile.size();
                int numStrikes = newDiscardSize - discardSize;

                for (int i = 0; i < numStrikes; i++) {
                    DamageInfo damageInfo = new DamageInfo(AbstractDungeon.player, baseDamage, DamageType.NORMAL);
                    int chosenAttackIndex = AbstractDungeon.miscRng.random(POSSIBLE_ATTACK_EFFECTS.length - 1);
                    AbstractDungeon.actionManager.addToTop(new DamageAction(target, damageInfo, POSSIBLE_ATTACK_EFFECTS[chosenAttackIndex]));
                }
            }
        }
    }
}

