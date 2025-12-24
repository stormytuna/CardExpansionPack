package cardexpansionpack.cards.blue;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.powers.CryogenicsPower;
import cardexpansionpack.util.CardStats;

public class Cryogenics extends BaseCard {
    public static final String ID = makeID("Cryogenics");

    private static final CardStats INFO = new CardStats(
        CardColor.BLUE,
        CardType.POWER,
        CardRarity.UNCOMMON,
        CardTarget.SELF,
        2
    );

    public Cryogenics() {
        super(ID, INFO);
        setCostUpgrade(1);
    }

    @Override
    public AbstractCard makeCopy() {
        return new Cryogenics();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new CryogenicsPower(p, 1)));
    }
}

