package cardexpansionpack.cards.blue;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LockOnPower;

import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.util.CardStats;

public class TunnelVision extends BaseCard {
    public static final String ID = makeID("TunnelVision");

    private static final CardStats INFO = new CardStats(
        CardColor.BLUE,
        CardType.SKILL,
        CardRarity.UNCOMMON,
        CardTarget.ENEMY,
        1
    );

    public TunnelVision() {
        super(ID, INFO);
        setCostUpgrade(0);
        setExhaust(true);
    }

    @Override
    public AbstractCard makeCopy() {
        return new TunnelVision();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new LockOnPower(m, 99)));
    }
}

