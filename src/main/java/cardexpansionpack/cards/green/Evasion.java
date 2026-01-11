package cardexpansionpack.cards.green;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.powers.EvasionPower;
import cardexpansionpack.util.CardStats;

public class Evasion extends BaseCard {
    public static final String ID = makeID("Evasion");

    private static final CardStats INFO = new CardStats(
        CardColor.GREEN,
        CardType.POWER,
        CardRarity.RARE,
        CardTarget.SELF,
        3
    );

    private static final int DEX = 2;
    private static final int DEX_UPGRADE = 1;

    public Evasion() {
        super(ID, INFO);
        setMagic(DEX, DEX_UPGRADE);
    }

    @Override
    public AbstractCard makeCopy() {
        return new Evasion();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new EvasionPower(p, magicNumber)));
    }
}
