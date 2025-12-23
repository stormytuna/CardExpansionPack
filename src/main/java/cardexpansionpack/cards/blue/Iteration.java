package cardexpansionpack.cards.blue;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.powers.IterationPower;
import cardexpansionpack.util.CardStats;

public class Iteration extends BaseCard {
    public static final String ID = makeID("Iteration");

    private static final CardStats INFO = new CardStats(
        CardColor.BLUE,
        CardType.SKILL,
        CardRarity.UNCOMMON,
        CardTarget.SELF,
        1
    );

    public Iteration() {
        super(ID, INFO);
        setExhaust(true, false);
    }

    @Override
    public AbstractCard makeCopy() {
        return new Iteration();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new IterationPower(p, 1)));
    }
}

