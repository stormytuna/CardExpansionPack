package cardexpansionpack.cards.red;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.HexPower;

import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.powers.ManiaPower;
import cardexpansionpack.util.CardStats;

public class Mania extends BaseCard {
    public static final String ID = makeID("Mania");

    private static final CardStats INFO = new CardStats(
        CardColor.RED,
        CardType.POWER,
        CardRarity.RARE,
        CardTarget.SELF,
        2
    );

    private static final int HEX_AMOUNT = 1;
    private static final int MANIA_AMOUNT = 1;

    public Mania() {
        super(ID, INFO);
        setEthereal(true, false);
    }

    @Override
    public AbstractCard makeCopy() {
        return new Mania();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new HexPower(p, HEX_AMOUNT)));
        addToBot(new ApplyPowerAction(p, p, new ManiaPower(p, MANIA_AMOUNT)));
    }
}

