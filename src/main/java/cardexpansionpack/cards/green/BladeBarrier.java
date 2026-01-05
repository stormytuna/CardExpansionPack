package cardexpansionpack.cards.green;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.util.CardStats;

public class BladeBarrier extends BaseCard {
    public static final String ID = makeID("BladeBarrier");

    private static final CardStats INFO = new CardStats(
        CardColor.GREEN,
        CardType.SKILL,
        CardRarity.UNCOMMON,
        CardTarget.SELF,
        1
    );

    public BladeBarrier() {
        super(ID, INFO);
        setExhaust(true);
    }

    @Override
    public AbstractCard makeCopy() {
        return new BladeBarrier();
    }

    private int getBlockAmount() {
        int block = 0;
        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisCombat) {
            if (c.cardID == Shiv.ID) {
                block++;
            }
        }

        if (upgraded) {
            block += 3;
        }

        return block;
    }

    @Override
    public void applyPowers() {
        baseBlock = getBlockAmount();

        super.applyPowers();

        if (upgraded) {
            rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        } else {
            rawDescription = cardStrings.DESCRIPTION;
        }
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));

        if (upgraded) {
            rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        } else {
            rawDescription = cardStrings.DESCRIPTION;
        }
        initializeDescription();
    }
}
