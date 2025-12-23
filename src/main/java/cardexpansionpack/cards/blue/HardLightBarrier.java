package cardexpansionpack.cards.blue;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.powers.HardLightBarrierPower;
import cardexpansionpack.util.CardStats;

public class HardLightBarrier extends BaseCard {
    public static final String ID = makeID("HardLightBarrier");

    private static final CardStats INFO = new CardStats(
        CardColor.BLUE,
        CardType.POWER,
        CardRarity.UNCOMMON,
        CardTarget.SELF,
        1
    );

    private static final int BLOCK_AMOUNT = 3;
    private static final int BLOCK_UPGRADE = 1;

    public HardLightBarrier() {
        super(ID, INFO);
        setMagic(BLOCK_AMOUNT, BLOCK_UPGRADE);
    }

    @Override
    public AbstractCard makeCopy() {
        return new HardLightBarrier();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToTop(new ApplyPowerAction(p, p, new HardLightBarrierPower(p, this.magicNumber)));
    }
}

