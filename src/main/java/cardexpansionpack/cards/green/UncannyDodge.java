package cardexpansionpack.cards.green;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.powers.UncannyDodgePower;
import cardexpansionpack.util.CardStats;

public class UncannyDodge extends BaseCard {
    public static final String ID = makeID("UncannyDodge");

    private static final CardStats INFO = new CardStats(
        CardColor.GREEN,
        CardType.SKILL,
        CardRarity.RARE,
        CardTarget.SELF,
        3
    );

    private static final int DEX = 2;
    private static final int DEX_UPGRADE = 1;

    public UncannyDodge() {
        super(ID, INFO);
        setMagic(DEX, DEX_UPGRADE);
    }

    @Override
    public AbstractCard makeCopy() {
        return new UncannyDodge();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new UncannyDodgePower(p, magicNumber)));
    }
}
