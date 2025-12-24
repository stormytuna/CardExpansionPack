package cardexpansionpack.cards.blue;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.powers.BetaTestingPower;
import cardexpansionpack.util.CardStats;

public class BetaTesting extends BaseCard {
    public static final String ID = makeID("BetaTesting");

    private static final CardStats INFO = new CardStats(
        CardColor.BLUE,
        CardType.POWER,
        CardRarity.RARE,
        CardTarget.SELF,
        1
    );

    public BetaTesting() {
        super(ID, INFO);
        setEthereal(true, false);
    }

    @Override
    public AbstractCard makeCopy() {
        return new BetaTesting();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new BetaTestingPower(p, 2)));
    }
}

