/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.massedynamic.eclipse.jmx.model;

import java.util.Set;

import javax.management.ObjectName;

import org.massedynamic.eclipse.core.model.DataModelSourceException;
import org.massedynamic.eclipse.jmx.data.Domain;
import org.massedynamic.eclipse.jmx.data.JmxConnection;
import org.massedynamic.eclipse.jmx.data.MBean;
import org.massedynamic.eclipse.jmx.data.ObjectNameKeyValue;

/**
 * TODO: Comment.
 * 
 * @author Mark Masse
 */
public class MBeanModelSource extends AbstractObjectNameKeyValueModelSource<MBeanModel, ObjectName, MBean> {

    /**
     * TODO: Comment.
     * 
     * @param domainModel
     * @param jmxConnectionModel
     */
    public MBeanModelSource(DomainModel domainModel, JmxConnectionModel jmxConnectionModel) {
        super(domainModel, jmxConnectionModel);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.massedynamic.eclipse.zookeeper.model.DataModelSource#getData(java.lang.Object)
     */
    @Override
    public MBean getData(ObjectName objectName) throws DataModelSourceException {
        Domain domain = getDomain();
        Set<ObjectName> mbeanObjectNames = domain.getMBeanObjectNames();
        if (!mbeanObjectNames.contains(objectName)) {
            return null;
        }

        JmxConnection jmxConnection = getJmxConnection();
        return jmxConnection.getMBean(objectName);
    }

    @Override
    public Set<ObjectName> getKeys() throws DataModelSourceException {
        return getDomain().getMBeanObjectNames();
    }

    @Override
    public boolean isGetKeysSupported() {
        return true;
    }

    @Override
    protected MBeanModel createModelInternal(ObjectName objectName) {
        return new MBeanModel(objectName, getDomainModel(), getJmxConnectionModel());
    }

    /*
     * (non-Javadoc)
     * 
     * @seeorg.massedynamic.eclipse.zookeeper.model.jmx.AbstractObjectNameKeyValueModelSource#getKey(org.massedynamic.
     * eclipse.zookeeper.data.jmx. ObjectNameKeyValue )
     */
    @Override
    protected ObjectName getKey(ObjectNameKeyValue objectNameKeyValue) {

        if (!objectNameKeyValue.isMBean()) {
            return null;
        }

        return objectNameKeyValue.getObjectName();

    }

}
