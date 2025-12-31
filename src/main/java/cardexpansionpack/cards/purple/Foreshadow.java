package cardexpansionpack.cards.purple;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.powers.ForeshadowPower;
import cardexpansionpack.util.CardStats;

public class Foreshadow extends BaseCard {
    public static final String ID = makeID("Foreshadow");

    private static final CardStats INFO = new CardStats(
        CardColor.PURPLE,
        CardType.POWER,
        CardRarity.UNCOMMON,
        CardTarget.SELF,
        0
    );

    public Foreshadow() {
        super(ID, INFO);
        setInnate(false, true);
    }

    @Override
    public AbstractCard makeCopy() {
        return new Foreshadow();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new ForeshadowPower(p)));
    }
}

