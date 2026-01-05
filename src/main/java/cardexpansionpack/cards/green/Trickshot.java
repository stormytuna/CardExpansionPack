package cardexpansionpack.cards.green;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.powers.TrickshotPower;
import cardexpansionpack.util.CardStats;

public class Trickshot extends BaseCard {
    public static final String ID = makeID("Trickshot");

    private static final CardStats INFO = new CardStats(
        CardColor.GREEN,
        CardType.POWER,
        CardRarity.RARE,
        CardTarget.SELF,
        2
    );

    private static final int NUM_SHIVS = 2;

    public Trickshot() {
        super(ID, INFO);
        setCostUpgrade(1);
        setMagic(NUM_SHIVS);
        cardsToPreview = new Shiv();
    }

    @Override
    public AbstractCard makeCopy() {
        return new Trickshot();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new MakeTempCardInHandAction(new Shiv(), magicNumber));
        addToBot(new ApplyPowerAction(p, p, new TrickshotPower(p)));
    }
}
