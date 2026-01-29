package cardexpansionpack.cards.red;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.util.CardStats;

public class Relentless extends BaseCard {
    public static final String ID = makeID("Relentless");

    private static final CardStats INFO = new CardStats(
        CardColor.RED,
        CardType.ATTACK,
        CardRarity.COMMON,
        CardTarget.ENEMY,
        1
    );

    private static final int DAMAGE = 9;
    private static final int DAMAGE_UPGRADE = 3;

    public Relentless() {
        super(ID, INFO);
        setDamage(DAMAGE, DAMAGE_UPGRADE);
    }

    @Override
    public AbstractCard makeCopy() {
        return new Relentless();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        DamageInfo info = new DamageInfo(p, damage, DamageType.NORMAL);
        addToBot(new DamageAction(m, info, AttackEffect.BLUNT_HEAVY));
    }
    
    @SpirePatch2(clz = AbstractPlayer.class, method = "applyStartOfTurnPostDrawRelics")
    public static class AddBackReckless {
        @SpirePostfixPatch
        public static void patch(AbstractPlayer __instance) {
            for (AbstractCard c : __instance.discardPile.group) {
                if (c instanceof Relentless) {
                    AbstractDungeon.actionManager.addToBottom(new DiscardToHandAction(c));
                }
            }
        }
    }
}

