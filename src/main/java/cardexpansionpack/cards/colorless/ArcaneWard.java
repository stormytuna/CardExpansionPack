package cardexpansionpack.cards.colorless;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BufferPower;

import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.util.CardStats;

public class ArcaneWard extends BaseCard {
    public static final String ID = makeID("ArcaneWard");

    private static final CardStats INFO = new CardStats(
        CardColor.COLORLESS,
        CardType.SKILL,
        CardRarity.RARE,
        CardTarget.SELF,
        1
    );

    public ArcaneWard() {
        super(ID, INFO);
        setEthereal(true, false);
        setExhaust(true);
    }

    @Override
    public AbstractCard makeCopy() {
        return new ArcaneWard();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new BufferPower(p, 1)));
    }
}
