package cardexpansionpack.cards.red;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.util.CardStats;

public class Tenacity extends BaseCard {
    public static final String ID = makeID("Tenacity");

    private static final CardStats INFO = new CardStats(
        CardColor.RED,
        CardType.SKILL,
        CardRarity.UNCOMMON,
        CardTarget.SELF,
        4
    );

    private static final int BLOCK = 10;
    private static final int BLOCK_UPGRADE = 4;

    public Tenacity() {
        super(ID, INFO);
        setBlock(BLOCK, BLOCK_UPGRADE);
    }

    @Override
    public AbstractCard makeCopy() {
        return new Tenacity();
    }

    public void updateCost() {
        setCostForTurn(cost - TrackNumExhaustedCards.numCardsExhaustedThisCombat);
    }

    @Override
    public void triggerWhenDrawn() {
        super.triggerWhenDrawn();
        updateCost();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
    }

    @SpirePatch2(clz = CardGroup.class, method = "moveToExhaustPile")
    public static class TrackNumExhaustedCards {
        public static int numCardsExhaustedThisCombat = 0;

        @SpirePostfixPatch
        public static void patch() {
            numCardsExhaustedThisCombat++;

            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                if (c instanceof Tenacity) {
                    ((Tenacity)c).updateCost();
                }
            }

            for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
                if (c instanceof Tenacity) {
                    ((Tenacity)c).updateCost();
                }
            }

            for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
                if (c instanceof Tenacity) {
                    ((Tenacity)c).updateCost();
                }
            }
        }
    }

    @SpirePatch2(clz = AbstractRoom.class, method = "endBattle")
    public static class ResetNumExhaustedCards {
        @SpirePostfixPatch
        public static void patch() {
            TrackNumExhaustedCards.numCardsExhaustedThisCombat = 0;
        }
    }
}

