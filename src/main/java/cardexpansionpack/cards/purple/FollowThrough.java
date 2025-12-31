package cardexpansionpack.cards.purple;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EquilibriumPower;

import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.util.CardStats;

public class FollowThrough extends BaseCard {
    public static final String ID = makeID("FollowThrough");

    private static final CardStats INFO = new CardStats(
        CardColor.PURPLE,
        CardType.ATTACK,
        CardRarity.UNCOMMON,
        CardTarget.ALL_ENEMY,
        2
    );

    private static final int DAMAGE = 10;
    private static final int DAMAGE_UPGRADE = 4;

    public FollowThrough() {
        super(ID, INFO);
        setDamage(DAMAGE, DAMAGE_UPGRADE);
    }

    @Override
    public AbstractCard makeCopy() {
        return new FollowThrough();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAllEnemiesAction(p, damage, DamageType.NORMAL, AttackEffect.BLUNT_HEAVY));
        addToBot(new ApplyPowerAction(p, p, new EquilibriumPower(p, 1), 1));
    }
}

