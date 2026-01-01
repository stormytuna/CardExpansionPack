package cardexpansionpack.cards.purple;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.stances.NeutralStance;

import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.powers.SuppressPower;
import cardexpansionpack.util.CardStats;

public class Suppress extends BaseCard {
    public static final String ID = makeID("Suppress");

    private static final CardStats INFO = new CardStats(
        CardColor.PURPLE,
        CardType.SKILL,
        CardRarity.RARE,
        CardTarget.SELF,
        1
    );

    public Suppress() {
        super(ID, INFO);
        setCostUpgrade(0);
        setExhaust(true);
    }

    @Override
    public AbstractCard makeCopy() {
        return new Suppress();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p.hasPower(SuppressPower.ID)) {
            return;
        }

        addToBot(new ApplyPowerAction(p, p, new SuppressPower(p, p, p.stance)));
        addToBot(new ChangeStanceAction(NeutralStance.STANCE_ID));
    }
}

