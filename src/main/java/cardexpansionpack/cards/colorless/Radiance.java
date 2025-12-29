package cardexpansionpack.cards.colorless;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.powers.RadiancePower;
import cardexpansionpack.util.CardStats;

public class Radiance extends BaseCard {
    public static final String ID = makeID("Radiance");

    private static final CardStats INFO = new CardStats(
        CardColor.COLORLESS,
        CardType.POWER,
        CardRarity.RARE,
        CardTarget.SELF,
        1
    );

    public Radiance() {
        super(ID, INFO);
        setInnate(false, true);
    }

    @Override
    public AbstractCard makeCopy() {
        return new Radiance();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!p.hasPower(RadiancePower.ID)) {
            addToBot(new ApplyPowerAction(p, p, new RadiancePower(p)));
        }
    }
}
