package cardexpansionpack.cards.red;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.util.CardStats;

public class Pyre extends BaseCard {
    public static final String ID = makeID("Pyre");

    private static final CardStats INFO = new CardStats(
        CardColor.RED,
        CardType.SKILL,
        CardRarity.RARE,
        CardTarget.SELF,
        1
    );

    private static final int EXHAUST_CARDS_REQUIRED = 3;
    private static final int EXHAUST_CARDS_REQUIRED_UPGRADE = -1;

    public Pyre() {
        super(ID, INFO);
        setMagic(EXHAUST_CARDS_REQUIRED, EXHAUST_CARDS_REQUIRED_UPGRADE);
        setExhaust(true);
    }

    @Override
    public AbstractCard makeCopy() {
        return new Pyre();
    }

    private int getStrengthAmount() {
        return AbstractDungeon.player.exhaustPile.size() / magicNumber;
    }

    @Override
    public void applyPowers() {
        super.applyPowers();

        rawDescription = cardStrings.DESCRIPTION;
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + getStrengthAmount() + cardStrings.EXTENDED_DESCRIPTION[1];
        initializeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int strengthAmount = getStrengthAmount();
        if (strengthAmount <= 0) {
            return;
        }

        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, strengthAmount), strengthAmount));

        rawDescription = cardStrings.DESCRIPTION;
        initializeDescription();
    }
}

