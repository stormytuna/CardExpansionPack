package cardexpansionpack.cards.green;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.powers.UnderhandedTacticsPower;
import cardexpansionpack.util.CardStats;

public class UnderhandedTactics extends BaseCard {
    public static final String ID = makeID("UnderhandedTactics");

    private static final CardStats INFO = new CardStats(
        CardColor.GREEN,
        CardType.POWER,
        CardRarity.RARE,
        CardTarget.SELF,
        1
    );

    private static final int AMOUNT = 1;
    private static final int AMOUNT_UPGRADE = 1;

    public UnderhandedTactics() {
        super(ID, INFO);
        setMagic(AMOUNT, AMOUNT_UPGRADE);
    }

    @Override
    public AbstractCard makeCopy() {
        return new UnderhandedTactics();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new UnderhandedTacticsPower(p, magicNumber)));
    }
}
