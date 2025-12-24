package cardexpansionpack.cards.blue;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.powers.FirewallPower;
import cardexpansionpack.util.CardStats;

public class Firewall extends BaseCard {
    public static final String ID = makeID("Firewall");

    private static final CardStats INFO = new CardStats(
        CardColor.BLUE,
        CardType.POWER,
        CardRarity.UNCOMMON,
        CardTarget.SELF,
        1
    );

    private static final int POWER_STACKS = 1;
    private static final int POWER_STACKS_UPGRADE = 1;

    public Firewall() {
        super(ID, INFO);
        setMagic(POWER_STACKS, POWER_STACKS_UPGRADE);
    }

    @Override
    public AbstractCard makeCopy() {
        return new Firewall();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new FirewallPower(p, this.magicNumber)));
    }
}

