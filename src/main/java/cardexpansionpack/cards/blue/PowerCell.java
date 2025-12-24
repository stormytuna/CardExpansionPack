package cardexpansionpack.cards.blue;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.powers.PowerCellPower;
import cardexpansionpack.util.CardStats;

public class PowerCell extends BaseCard {
    public static final String ID = makeID("PowerCell");

    private static final CardStats INFO = new CardStats(
        CardColor.BLUE,
        CardType.POWER,
        CardRarity.UNCOMMON,
        CardTarget.SELF,
        2
    );

    private static final int ENERGY_GAIN = 1;

    public PowerCell() {
        super(ID, INFO);
        setMagic(ENERGY_GAIN);
        setInnate(false, true);
    }

    @Override
    public AbstractCard makeCopy() {
        return new PowerCell();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new PowerCellPower(p, ENERGY_GAIN)));
    }
}

