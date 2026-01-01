package cardexpansionpack.cards.purple;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.powers.PierceTheVeilPower;
import cardexpansionpack.util.CardStats;

public class PierceTheVeil extends BaseCard {
    public static final String ID = makeID("PierceTheVeil");

    private static final CardStats INFO = new CardStats(
        CardColor.PURPLE,
        CardType.POWER,
        CardRarity.UNCOMMON,
        CardTarget.SELF,
        2
    );

    private static final int POWER_AMOUNT = 5;

    public PierceTheVeil() {
        super(ID, INFO);
        setMagic(POWER_AMOUNT);
        setCostUpgrade(1);
    }

    @Override
    public AbstractCard makeCopy() {
        return new PierceTheVeil();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new PierceTheVeilPower(p, magicNumber)));
    }
}

